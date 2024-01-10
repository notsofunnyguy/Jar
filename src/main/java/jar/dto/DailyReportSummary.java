package jar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyReportSummary {
    private long transactionsCount;
    private double creditedAmount;
    private double debitedAmount;

    public static DailyReportSummary getDailyReportSummary(){
        return new DailyReportSummary();
    }
}
