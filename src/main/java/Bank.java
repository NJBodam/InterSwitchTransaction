import java.util.List;

public class Bank {
    private Long id;
    private String name;
    private Double balance;
    private List<String> transactionHistory;

    public Bank(Long id, String name, Double balance, List<String> transactionHistory) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }


    public void setName(String name) {
        this.name = name;
    }



    synchronized void credit(Double amount, String bankName) {
        if(amount >= 0) {
            balance += amount;
            transactionHistory.add("Credit of " + amount + " from Bank " + bankName);
        }
    }

    synchronized void debit(Double amount, String bankName) {
        if(balance >= amount) {
            balance -= amount;
            transactionHistory.add("Debit of " + amount + " to Bank " + bankName);
        } else System.out.println("You can not withdraw " + amount
                + ", your balance is " + balance);
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

    public synchronized Double getBalance() {
        return balance;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setBalance(Double balance) {
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
