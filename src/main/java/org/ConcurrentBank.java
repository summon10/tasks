import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

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
        accountTo.deposit(amount);
    }

    public BigDecimal getTotalBalance() {
        BigDecimal total = new BigDecimal(0);
        for (BankAccount account : accounts.values()) {
            total.add(account.getBalance());
        }
        return total;
    }
}
