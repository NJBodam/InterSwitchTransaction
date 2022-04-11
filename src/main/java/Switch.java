import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Switch extends Thread{

    private final Bank bankA;
    private final Bank bankB;
    private final String transactionType;
    private final Long amount;
    private static final Hashtable<String, List<String>> bankStatement = new Hashtable<>();

    public Switch(Bank bankA, Bank bankB, String transactionType, Long amount) {
        this.bankA = bankA;
        this.bankB = bankB;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public synchronized static void transact(String transactionType, Long amount, Bank bankA, Bank bankB) {

        if(transactionType.equals("Debit")) {
            if (amount <= bankA.getBalance()) {
                bankA.debit(amount, bankB.getName());
                bankB.credit(amount, bankA.getName());
                bankStatement.put(bankA.getName(), bankA.getTransactionHistory());
                bankStatement.put(bankB.getName(), bankB.getTransactionHistory());
                System.err.println(Thread.currentThread() + " Transaction Successful");
            } else {
                System.err.println(Thread.currentThread() + " transaction failed: Credit Transfer of " + amount + " from " + bankA.getName() + " to " + bankB.getName());
            }

        } else if(transactionType.equals("Credit")) {
            if (amount <= bankB.getBalance()) {
                bankA.credit(amount, bankB.getName());
                bankB.debit(amount, bankA.getName());
                bankStatement.put(bankA.getName(), bankA.getTransactionHistory());
                bankStatement.put(bankB.getName(), bankB.getTransactionHistory());
                System.err.println(Thread.currentThread() + " Transaction Successful");
            } else {
                System.err.println(Thread.currentThread() + " transaction failed: Credit Transfer of " + amount + " from " + bankB.getName() + " to " + bankA.getName());
            }
        } else System.err.println("Invalid Transaction Type");
    }

    public synchronized static Hashtable<String, List<String>> getSwitchStatement() {
        return bankStatement;
    }

    public synchronized static List<String> getBankStatement(Bank bank) {
        return bankStatement.get(bank.getName());
    }

    public void run() {
        transact(transactionType, amount, bankA, bankB);
    }
}
