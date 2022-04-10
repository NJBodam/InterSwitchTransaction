import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        Bank bankA = new Bank(1234L, "Access Bank", 50000D, listA);
//        bankA.setName("Sterling Bank");
//        bankA.setBalance(50000D);
//        bankA.setId(1234L);
//        bankA.set
        Bank bankB = new Bank(2345L, "Sterling Bank", 50000D, listB);

//        Bank bankB = new Bank();
//        bankB.setName("Access Bank");
//        bankB.setBalance(50000D);
//        bankB.setId(2345L);

        Switch t1 = new Switch(bankA, bankB, "Debit", 10000D);
        Switch t2 = new Switch(bankA, bankB, "Debit", 5000D);
        Switch t3 = new Switch(bankA, bankB, "Debit", 5000D);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();


        System.out.println(bankA.getName() + ">>>>>" + bankA.getTransactionHistory());
        System.out.println(bankB.getName() + ">>>>>" + bankB.getTransactionHistory());
       // bankB.getBankStatement();
        System.out.println(bankA.getName() + ">>>" + bankA.getBalance());
        System.out.println(bankB.getName() + ">>>" + bankB.getBalance());



    }
}
