import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    private static long toCents(BigDecimal amount) {

        return amount.multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .longValueExact();
    }

    private static BigDecimal fromCents(long cents) {
        return BigDecimal.valueOf(cents, 2);
    }

    public BankAccount createAccount(BigDecimal balance)
    {
        long id = nextId.getAndIncrement();
        BankAccount account = new BankAccount(id, balance);
        accounts.put(id, account);
        return account;
    }

    public synchronized void transfer(BankAccount accountFrom, BankAccount accountTo, BigDecimal amount){
        if (accountFrom == null || accountTo == null) throw new IllegalArgumentException("Incorrect Account");
        accountFrom.withdraw(amount);
        try {
            accountTo.deposit(amount);
        } catch (Exception e) {
            accountFrom.deposit(amount);
            throw new RuntimeException("Перевод временно недоступен", e);
        }
    }

    public BigDecimal getTotalBalance() {
        long totalCents = 0;
        for (BankAccount account : accounts.values()) {
            totalCents += toCents(account.getBalance());
        }
        return fromCents(totalCents);
    }
}
