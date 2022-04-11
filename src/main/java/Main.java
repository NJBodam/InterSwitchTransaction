import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        Bank bankA = new Bank(1234L, "Access Bank", 50000L, listA);
//        bankA.setName("Sterling Bank");
//        bankA.setBalance(50000D);
//        bankA.setId(1234L);
//        bankA.set
        Bank bankB = new Bank(2345L, "Sterling Bank", 50000L, listB);

//        Bank bankB = new Bank();
//        bankB.setName("Access Bank");
//        bankB.setBalance(50000D);
//        bankB.setId(2345L);

        Switch t1 = new Switch(bankA, bankB, "Debit", 10000L);
        Switch t2 = new Switch(bankA, bankB, "Debit", 5000L);
        Switch t3 = new Switch(bankA, bankB, "Debit", 5000L);
        Switch t4 = new Switch(bankA, bankB, "Credit", 5000L);
        Switch t5 = new Switch(bankA, bankB, "Credit", 5000L);
        Switch t6 = new Switch(bankA, bankB, "Credit", 10000L);
        Switch t7 = new Switch(bankA, bankB, "Credit", 100000L);
        Switch t8 = new Switch(bankA, bankB, "Credit", 25000L);
        Switch t9 = new Switch(bankA, bankB, "Credit", 25000L);
      //  Switch t10 = new Switch(bankA, bankB, "Credit", 10000D);





        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
   //     t10.start();

        System.out.println(">>>>>>" + Switch.getBankStatement(bankA));
        System.out.println(">>>>>>" + Switch.getBankStatement(bankB));

        System.out.println("Bank A Balane: " + bankA.getBalance());
        System.out.println("Bank B Balance: " + bankB.getBalance());


//        System.out.println(bankA.getName() + ">>>>>" + bankA.getTransactionHistory());
//        System.out.println(bankB.getName() + ">>>>>" + bankB.getTransactionHistory());

          //bankA.getBankStatement();
//        System.out.println(bankA.getName() + ">>>" + bankA.getBalance());
//        System.out.println(bankB.getName() + ">>>" + bankB.getBalance());



    }
}
