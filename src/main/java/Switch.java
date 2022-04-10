import java.util.List;

public class Switch extends Thread{

    private final Bank bankA;
    private final Bank bankB;
    private final String transactionType;
    private final Double amount;

    public Switch(Bank bankA, Bank bankB, String transactionType, Double amount) {
        this.bankA = bankA;
        this.bankB = bankB;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void transact(String transactionType, Double amount, Bank bankA, Bank bankB) {
        if(transactionType.equals("Debit")) {
            bankA.debit(amount, bankB.getName());
            bankB.credit(amount, bankA.getName());

//            bankA.getTransactionHistory().add("Debit of " + amount + " to Bank " + bankB.getName());
//
//            bankB.getTransactionHistory().add("Credit of " + amount + " from Bank " + bankA.getName());

        } else if(transactionType.equals("Credit")) {
            bankA.credit(amount, bankB.getName());
            bankB.debit(amount, bankA.getName());

//            bankA.getTransactionHistory().add("Credit of " + amount + " from Bank " + bankB.getName());
//
//            bankB.getTransactionHistory().add("Debit of " + amount + " to Bank " + bankA.getName());
        } else System.out.println("Invalid Transaction Type");
    }

    public void run() {
        transact(transactionType, amount, bankA, bankB);
    }
}
