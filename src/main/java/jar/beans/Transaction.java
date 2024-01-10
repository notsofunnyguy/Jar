package jar.beans;

import jakarta.persistence.*;
import jar.dto.TransactionCurrencyConversionResponseDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    private double amount;
    private String currency;
    private long accountingDate;
}
