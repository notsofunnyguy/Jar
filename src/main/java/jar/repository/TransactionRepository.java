package jar.repository;

import jar.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCurrency(String currency);

    List<Transaction> findByAccountingDateBetween(long dateStartTime, long dateEndTime);
}
