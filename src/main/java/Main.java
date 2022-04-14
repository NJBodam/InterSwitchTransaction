import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        Bank bankA = new Bank(1234L, "Access Bank", 500000, listA);

        Bank bankB = new Bank(2345L, "Sterling Bank", 500000, listB);

        Bank bankC = new Bank(345L, "First Bank", 550000, new ArrayList<>());



        Switch t1 = new Switch(bankC, bankB, "Debit", 10000);
        Switch t2 = new Switch(bankA, bankC, "Debit", 5000);
        Switch t3 = new Switch(bankA, bankB, "Debit", 5000);
        Switch t4 = new Switch(bankC, bankA, "Credit", 5000);
        Switch t5 = new Switch(bankC, bankB, "Credit", 5000);
        Switch t6 = new Switch(bankA, bankB, "Credit", 10000);
        Switch t7 = new Switch(bankC, bankA, "Credit", 100000);
        Switch t8 = new Switch(bankA, bankB, "Credit", 25000);
        Switch t9 = new Switch(bankB, bankC, "Credit", 25000);
       Switch t10 = new Switch(bankA, bankB, "Credit", 10000);

        Switch t11 = new Switch(bankA, bankC, "Debit", 5000);
        Switch t12 = new Switch(bankA, bankB, "Debit", 5000);
        Switch t13 = new Switch(bankC, bankA, "Credit", 5000);
        Switch t14 = new Switch(bankC, bankB, "Credit", 5000);

        Switch t15 = new Switch(bankA, bankB, "Debit", 25000);
        Switch t16 = new Switch(bankB, bankC, "Credit", 25000);
        Switch t17 = new Switch(bankA, bankB, "Credit", 10000);

        Switch t18 = new Switch(bankC, bankA, "Credit", 5000);
        Switch t19 = new Switch(bankC, bankB, "Debit", 5000);
        Switch t20 = new Switch(bankA, bankB, "Credit", 10000);



       // These are the threads with the .join() that achieves synchronicity
        threads(t1, t2, t3, t4, t5);
        threads(t6, t7, t8, t9, t10);
        threads(t11, t12, t13, t14, t15);
        threads(t16, t17, t18, t19, t20);

        // These are the threads without .join(), they have synchronized methods within
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//        t6.start(); t7.start(); t8.start(); t9.start(); t10.start();

        System.out.println(">>>>>>" + Switch.getBankStatement(bankA));
        System.out.println(">>>>>>" + Switch.getBankStatement(bankB));
        System.out.println(">>>>>>" + Switch.getBankStatement(bankC));

        System.out.println(bankA.getName() + " Balance: " + bankA.getBalance());
        System.out.println(bankB.getName() + " Balance: " + bankB.getBalance());
        System.out.println(bankC.getName() + " Balance: " + bankC.getBalance());

        Hashtable<String, List<String>> switchStatement = Switch.getSwitchStatement();
        System.err.println(">>>>>>> KeySet " + switchStatement.keySet());
        System.err.println(">>>>>>> EntrySet " + switchStatement.entrySet());
        System.err.println(">>>>>>> Values" + switchStatement.values());


        System.err.println(">>>>>>> Today's volume: " + Switch.getTransactionVolume().get(LocalDate.now()));



//        System.out.println(bankA.getName() + ">>>>>" + bankA.getTransactionHistory());
//        System.out.println(bankB.getName() + ">>>>>" + bankB.getTransactionHistory());

          //bankA.getBankStatement();
//        System.out.println(bankA.getName() + ">>>" + bankA.getBalance());
//        System.out.println(bankB.getName() + ">>>" + bankB.getBalance());



    }


    private static void threads(Switch t6, Switch t7, Switch t8, Switch t9, Switch t10) throws InterruptedException {
        t6.start();
        t6.join();
        t7.start();
        t7.join();
        t8.start();
        t8.join();
        t9.start();
        t9.join();
        t10.start();
        t10.join();
    }
}
