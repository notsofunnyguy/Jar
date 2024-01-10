package jar.service;

import jar.beans.Transaction;
import jar.dto.GlobalCurrencyConverterDto;
import jar.dto.TransactionCurrencyConversionResponseDto;
import jar.dto.TransactionRequestDto;
import jar.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import jar.utils.TimeUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(TransactionRequestDto transactionRequestDto) {
        return transactionRepository.save(transactionRequestDto.getTransaction());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByCurrency(String currency) {
        return transactionRepository.findByCurrency(currency);
    }

    @Override
    public List<Transaction> getTransactionsByDate(String date) {
        long dateStartTime = TimeUtils.getDateStart(date);
        long dateEndTime = TimeUtils.getDateEnd(date);
        return transactionRepository.findByAccountingDateBetween(dateStartTime, dateEndTime);
    }

    @Override
    public List<TransactionCurrencyConversionResponseDto> getAllTransactionsConvertedInTargetCurrency(String currency) {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionCurrencyConversionResponseDto> TransactionCurrencyConversionResponseDto = convertToCurrencyConversionResponseDtoList(transactions);
        return giveAllTransactionsInCurrency(TransactionCurrencyConversionResponseDto, currency);
    }

    private List<TransactionCurrencyConversionResponseDto> giveAllTransactionsInCurrency(List<TransactionCurrencyConversionResponseDto> transactions, String currency) {
        String targetUrl = "https://api.fxratesapi.com/latest";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GlobalCurrencyConverterDto> responseEntity = restTemplate
                .exchange(targetUrl, HttpMethod.GET, entity, GlobalCurrencyConverterDto.class);
        transactions.forEach(
                transaction -> {
                    if(transaction.getCurrency().equalsIgnoreCase(currency)){
                        transaction.setConvertedAmount(transaction.getAmount());
                    }else {
                        double amountInUSD = transaction.getAmount() / responseEntity.getBody().getRates().get(transaction.getCurrency());
                        double amountInRequiredCurrency = amountInUSD * responseEntity.getBody().getRates().get(currency);
                        transaction.setConvertedAmount(amountInRequiredCurrency);
                    }
                    transaction.setTargetCurrency(currency);
                }
        );
        return transactions;
    }

    public List<TransactionCurrencyConversionResponseDto> convertToCurrencyConversionResponseDtoList(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::convertToCurrencyConversionResponseDto)
                .collect(Collectors.toList());
    }

    private TransactionCurrencyConversionResponseDto convertToCurrencyConversionResponseDto(Transaction transaction) {
        TransactionCurrencyConversionResponseDto responseDto = new TransactionCurrencyConversionResponseDto();
        responseDto.setTransactionId(transaction.getTransactionId());
        responseDto.setAmount(transaction.getAmount());
        responseDto.setCurrency(transaction.getCurrency());
        responseDto.setAccountingDate(transaction.getAccountingDate());

        return responseDto;
    }




}
