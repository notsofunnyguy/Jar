package jar.service;

import jar.beans.Transaction;
import jar.dto.TransactionRequestDto;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(TransactionRequestDto transactionRequestDto);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByCurrency(String currency);

    List<Transaction> getTransactionsByDate(String date);

    List<Transaction> getAllTransactionsInCurrency(String currency);
}
