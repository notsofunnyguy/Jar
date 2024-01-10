package jar.beans;

import jakarta.persistence.*;
import lombok.Data;

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
