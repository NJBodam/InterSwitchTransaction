import java.util.List;

public class Bank {
    private Long id;
    private String name;
    private volatile int balance;
    private List<String> transactionHistory;

    public Bank(Long id, String name, int balance, List<String> transactionHistory) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }


    public void setName(String name) {
        this.name = name;
    }


    synchronized void transact(String transactionType, int amount, String bankName) {
        if (transactionType.equals("Credit")) {
            credit(amount, bankName);
        } else if (transactionType.equals("Debit")) {
            debit(amount, bankName);
        }
    }

    private void credit(int amount, String bankName) {
            balance += amount;
            transactionHistory.add("Credit of " + amount + " from Bank " + bankName + ". Bank balance: " + balance + ";");
    }

    private void debit(int amount, String bankName) {
            balance -= amount;
            transactionHistory.add("Debit of " + amount + " to Bank " + bankName + ". Bank balance: " + balance + ";");
    }


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setBalance(int balance) {
        balance = balance;
    }

    public synchronized List<String> getTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name=" + name +
                ", balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}
