package jar.dto;

import jar.beans.Transaction;
import lombok.Data;

@Data
public class TransactionRequestDto extends Money{

    public Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCurrency(getCurrency());
        transaction.setAmount(getAmount());
        transaction.setAccountingDate(System.currentTimeMillis());
        return transaction;
    }
}
