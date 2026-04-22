import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class BankAccount {
 private AtomicReference<BigDecimal> balance;
 private final long id;

    public long getId() {
        return id;
    }



    public BankAccount(long id, BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
        this.id = id;
    }

    public void deposit(BigDecimal amount)
    {
       balance.updateAndGet(current -> current.add(amount));

    }

    public void withdraw(BigDecimal amount)
    {
        balance.updateAndGet(current -> {
            if (current.compareTo(amount) < 0) {
                throw new IllegalStateException("Недостаточно средств");
            }
            return current.subtract(amount);
        });
    }

    public  BigDecimal getBalance()
    {
        return balance.get();
    }

}
