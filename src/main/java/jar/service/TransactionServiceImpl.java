package jar.service;

import jar.beans.Transaction;
import jar.dto.GlobalCurrencyConverterDto;
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
    public List<Transaction> getAllTransactionsInCurrency(String currency) {
        List<Transaction> transactions = transactionRepository.findAll();
        return giveAllTransactionsInCurrency(transactions, currency);
    }

    private List<Transaction> giveAllTransactionsInCurrency(List<Transaction> transactions, String currency) {
        List<Transaction> transactionsWithDifferentCurrency =
                transactions.stream().filter(transaction -> !transaction.getCurrency().equalsIgnoreCase(currency)).toList();
        String targetUrl = "https://api.fxratesapi.com/latest";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GlobalCurrencyConverterDto> responseEntity = restTemplate
                .exchange(targetUrl, HttpMethod.GET, entity, GlobalCurrencyConverterDto.class);
        transactionsWithDifferentCurrency.forEach(
                transaction -> {
                    double amountInUSD = transaction.getAmount()/responseEntity.getBody().getRates().get(transaction.getCurrency());
                    double amountInRequiredCurrency = amountInUSD*responseEntity.getBody().getRates().get(currency);
                    transaction.setAmount(amountInRequiredCurrency);
                    transaction.setCurrency(currency);
                }
        );
        return transactions;
    }


}
