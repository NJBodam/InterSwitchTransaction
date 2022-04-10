import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Switch extends Thread{

    private final Bank bankA;
    private final Bank bankB;
    private final String transactionType;
    private final Double amount;
    private static final Hashtable<String, List<String>> bankStatement = new Hashtable<>();

    public Switch(Bank bankA, Bank bankB, String transactionType, Double amount) {
        this.bankA = bankA;
        this.bankB = bankB;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public synchronized static void transact(String transactionType, Double amount, Bank bankA, Bank bankB) {
        List<String> tempHist = new ArrayList<>();


        if(transactionType.equals("Debit")) {
            bankA.debit(amount, bankB.getName());
            bankB.credit(amount, bankA.getName());


            if (bankStatement.containsKey(bankA.getName())) {
                tempHist = bankStatement.get(bankA.getName());
                tempHist.add(s + amount + s2 + bankA.getName());
                bankStatement.replace(bankB.getName(), tempHist);
            } else {
                tempHist.add(s + amount + s2 + bankB.getName());
                bankStatement.putIfAbsent(bankB.getName(), tempHist);
            }


//            bankStatementFill(amount, bankB, bankA, tempHist, "Debit of ", " to ");
//
//         //   bankStatementFill(amount, bankA, bankB, tempHist, "Credit of ", " from ");

        } else if(transactionType.equals("Credit")) {
            bankA.credit(amount, bankB.getName());
            bankB.debit(amount, bankA.getName());

            bankStatementFill(amount, bankB, bankA, tempHist, "Credit of ", " from ");

            //bankStatementFill(amount, bankA, bankB, tempHist, "Debit of ", " to ");

        } else System.out.println("Invalid Transaction Type");
    }

    private static void bankStatementFill(Double amount, Bank bankA, Bank bankB, List<String> tempHist, String s, String s2) {
        if (bankStatement.containsKey(bankB.getName())) {
            tempHist = bankStatement.get(bankB.getName());
            tempHist.add(s + amount + s2 + bankA.getName());
            bankStatement.replace(bankB.getName(), tempHist);
        } else {
            tempHist.add(s + amount + s2 + bankB.getName());
            bankStatement.putIfAbsent(bankB.getName(), tempHist);
        }

        if (bankStatement.containsKey(bankA.getName())) {
            tempHist = bankStatement.get(bankA.getName());
            tempHist.add(s + amount + s2 + bankB.getName());
            bankStatement.replace(bankA.getName(), tempHist);
        } else {
            tempHist.add(s + amount + s2 + bankB.getName());
            bankStatement.putIfAbsent(bankA.getName(), tempHist);
        }



    }

    public synchronized static Hashtable<String, List<String>> getSwitchStatement() {
        return bankStatement;
    }

    public synchronized static List<String> getBankStatement(Bank bank) {
        List<String> transactionHistory = bankStatement.get(bank.getName());
        transactionHistory.add("Bank balance for " + bank.getName() + " is " + bank.getBalance());
        return transactionHistory;
    }

    public void run() {
        transact(transactionType, amount, bankA, bankB);
    }
}
