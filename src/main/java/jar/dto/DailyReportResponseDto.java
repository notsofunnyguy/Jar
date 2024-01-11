package jar.dto;

import jar.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyReportResponseDto {
    private String date;
    private long countOfTransactions;
    private Map<String, DailyReportSummary> dailyReportSummaryPerCurrency;
    List<Transaction> transactions;
}
