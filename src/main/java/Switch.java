import java.time.LocalDate;
import java.util.*;

public class Switch extends Thread{

    private final Bank bankA;
    private final Bank bankB;
    private final String transactionType;
    private final int amount;
    private static final Hashtable<String, List<String>> bankStatement = new Hashtable<>();
    private static final Map<LocalDate, Integer> transactionVolume = new HashMap<>();

    // Create another stactic field hashtable that stores volume of transactions
    // It should save today's date as the key and the the volume as the value
    // Value will be calculated by using the get value aggregating it.

    public Switch(Bank bankA, Bank bankB, String transactionType, int amount) {
        this.bankA = bankA;
        this.bankB = bankB;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public synchronized static void transact(String transactionType, int amount, Bank bankA, Bank bankB) {

        if(transactionType.equals("Debit")) {
            if (amount <= bankA.getBalance()) {
                bankA.transact(transactionType, amount, bankB.getName());
                bankB.transact("Credit", amount, bankA.getName());
                bankStatement.put(bankA.getName(), bankA.getTransactionHistory());
                bankStatement.put(bankB.getName(), bankB.getTransactionHistory());
                System.err.println(Thread.currentThread() + " Transaction Successful");
                collateTransactionVolume(amount);
            } else {
                System.err.println(Thread.currentThread() + " transaction failed: Credit Transfer of " + amount + " from " + bankA.getName() + " to " + bankB.getName()
                        + ". Bank Balances: " + bankA.getName() + " :" + bankA.getBalance() + ". " +bankB.getName() + " :" + bankB.getBalance());
            }

        } else if(transactionType.equals("Credit")) {
            if (amount <= bankB.getBalance()) {
                bankA.transact(transactionType, amount, bankB.getName());
                bankB.transact("Debit", amount, bankA.getName());
                bankStatement.put(bankA.getName(), bankA.getTransactionHistory());
                bankStatement.put(bankB.getName(), bankB.getTransactionHistory());
                System.err.println(Thread.currentThread() + " Transaction Successful");
                collateTransactionVolume(amount);
            } else {
                System.err.println(Thread.currentThread() + " transaction failed: Credit Transfer of " + amount + " from " + bankB.getName() + " to " + bankA.getName()
                        + ". Bank Balances: " + bankA.getName() + " :" + bankA.getBalance() + ". " +bankB.getName() + " :" + bankB.getBalance());
            }

        } else System.err.println("Invalid Transaction Type");
    }

    private static void collateTransactionVolume(int amount) {
        transactionVolume.putIfAbsent(LocalDate.now(), amount);
        int currentVolume = transactionVolume.get(LocalDate.now());
        transactionVolume.put(LocalDate.now(), currentVolume + amount);
    }

    public synchronized static Hashtable<String, List<String>> getSwitchStatement() {
        return bankStatement;
    }

    public synchronized static Map<LocalDate, Integer> getTransactionVolume() {
        return transactionVolume;
    }


    public synchronized static List<String> getBankStatement(Bank bank) {
        // Try block to check for exceptions

        bankStatement.get(bank.getName());

        return bankStatement.get(bank.getName());
    }

    public void run() {
        transact(transactionType, amount, bankA, bankB);
    }
}
