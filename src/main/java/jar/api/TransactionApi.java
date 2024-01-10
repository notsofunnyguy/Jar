package jar.api;

import jar.beans.Transaction;
import jar.dto.TransactionCurrencyConversionResponseDto;
import jar.dto.TransactionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jar.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionApi {

    private final TransactionService transactionService;

    // api to record transaction
    @PostMapping("")
    public Transaction saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        return transactionService.saveTransaction(transactionRequestDto);
    }

    // api to get all transactions
    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    // api to get transactions by currency
    @GetMapping("/{currency}")
    public List<Transaction> getTransactionsByCurrency(@PathVariable(name = "currency") String currency){
        return transactionService.getTransactionsByCurrency(currency);
    }

    // api to get all transactions in currency, if transaction is recorded in some other currency converted it into mentioned currency
    @GetMapping("/convert/{targetCurrency}")
    public List<TransactionCurrencyConversionResponseDto> getAllTransactionsConvertedInTargetCurrency(@PathVariable(name = "targetCurrency") String targetCurrency){
        return transactionService.getAllTransactionsConvertedInTargetCurrency(targetCurrency);
    }


}
