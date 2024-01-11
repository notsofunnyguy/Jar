package jar.service;

import jar.models.Transaction;
import jar.dto.TransactionCurrencyConversionResponseDto;
import jar.dto.TransactionRequestDto;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(TransactionRequestDto transactionRequestDto);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByCurrency(String currency);

    List<Transaction> getTransactionsByDate(String date);

    List<TransactionCurrencyConversionResponseDto> getAllTransactionsConvertedInTargetCurrency(String targetCurrency);
}
