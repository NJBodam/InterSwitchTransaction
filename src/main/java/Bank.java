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
            System.out.println("Balance after credit " + balance);
        }
    }

    synchronized void debit(Double amount, String bankName) {
        if(balance >= amount) {
            balance -= amount;
            transactionHistory.add("Debit of " + amount + " to Bank " + bankName);
            System.out.println("Balance after debit " + balance);
        } else System.out.println("You can not withdraw " + amount
                + ", your balance is " + balance);
    }

    public void getBankStatement() {
        for (String s : transactionHistory) {
            System.out.println(s);
        }
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

    public Double getBalance() {
        return balance;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setBalance(Double balance) {
        balance = balance;
    }

    public List<String> getTransactionHistory() {
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
