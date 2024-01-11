package jar.dto;

import jar.models.Transaction;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TransactionRequestDto extends Money{

    public Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCurrency(getCurrency());
        transaction.setAmount(getAmount());
        transaction.setAccountingDate(System.currentTimeMillis());
        return transaction;
    }
}
