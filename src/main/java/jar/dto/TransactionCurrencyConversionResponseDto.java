package jar.dto;

import jar.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCurrencyConversionResponseDto extends Transaction {
    private double convertedAmount;
    private String targetCurrency;
}
