package jar.service;

import jar.beans.Transaction;
import jar.dto.DailyReportRequestDto;
import jar.dto.DailyReportResponseDto;
import jar.dto.DailyReportSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DailyReportServiceImpl implements DailyReportService {

    private final TransactionService transactionService;

    @Override
    public DailyReportResponseDto getDailyReportByDate(DailyReportRequestDto dailyReportRequestDto) {
        List<Transaction> transactions = transactionService.getTransactionsByDate(dailyReportRequestDto.getDate());

        Map<String, DailyReportSummary> dailyReportSummaryPerCurrency = new HashMap<>();


        transactions.forEach(
                transaction -> {
                    dailyReportSummaryPerCurrency.computeIfAbsent(transaction.getCurrency(), v -> DailyReportSummary.getDailyReportSummary());
                    getUpdatedDailyReportSummaryAndTransaction(dailyReportSummaryPerCurrency.get(transaction.getCurrency()), transaction);
                }
        );

        return DailyReportResponseDto.builder()
                .date(dailyReportRequestDto.getDate())
                .countOfTransactions(transactions.size())
                .transactions(transactions)
                .dailyReportSummaryPerCurrency(dailyReportSummaryPerCurrency)
                .build();

    }

    private static void getUpdatedDailyReportSummaryAndTransaction(DailyReportSummary dailyReportSummary, Transaction transaction) {
        if(transaction.getAmount()>0){
            dailyReportSummary.setCreditedAmount(dailyReportSummary.getCreditedAmount() + transaction.getAmount());
        }else{
            dailyReportSummary.setDebitedAmount(dailyReportSummary.getDebitedAmount() + transaction.getAmount());
        }
        dailyReportSummary.setTransactionsCount(dailyReportSummary.getTransactionsCount() + 1);
    }
}
