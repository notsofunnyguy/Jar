package jar.jarAssignment.Jar.DailyReportTets;

import jar.models.Transaction;
import jar.dto.DailyReportRequestDto;
import jar.dto.DailyReportResponseDto;
import jar.dto.DailyReportSummary;
import jar.service.DailyReportServiceImpl;
import jar.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class DailyReportServiceImplTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private DailyReportServiceImpl dailyReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDailyReportByDate_shouldReturnCorrectResponse() {
        // Arrange
        String date = "10/01/2024";
        DailyReportRequestDto requestDto = new DailyReportRequestDto(date);
        List<Transaction> transactions = Arrays.asList(
                getTransaction("USD", 40.00),
                getTransaction("EUR", -50.0),
                getTransaction("USD", 30.0)
        );

        when(transactionService.getTransactionsByDate(date)).thenReturn(transactions);

        // Act
        DailyReportResponseDto responseDto = dailyReportService.getDailyReportByDate(requestDto);

        // Assert
        assertEquals(date, responseDto.getDate());
        assertEquals(3, responseDto.getCountOfTransactions());
        assertEquals(transactions, responseDto.getTransactions());

        Map<String, DailyReportSummary> dailyReportSummaryPerCurrency = responseDto.getDailyReportSummaryPerCurrency();
        assertEquals(2, dailyReportSummaryPerCurrency.size());

        DailyReportSummary usdSummary = dailyReportSummaryPerCurrency.get("USD");
        assertEquals(2, usdSummary.getTransactionsCount());
        assertEquals(70.0, usdSummary.getCreditedAmount());
        assertEquals(0.0, usdSummary.getDebitedAmount());

        DailyReportSummary eurSummary = dailyReportSummaryPerCurrency.get("EUR");
        assertEquals(1, eurSummary.getTransactionsCount());
        assertEquals(0.0, eurSummary.getCreditedAmount());
        assertEquals(-50.0, eurSummary.getDebitedAmount());

        // Verify that the transactionService.getTransactionsByDate was called once
        verify(transactionService, times(1)).getTransactionsByDate(date);
    }

    private static Transaction getTransaction(String currency, double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        return transaction;
    }
}

