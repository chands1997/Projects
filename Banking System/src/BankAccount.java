import java.util.ArrayList;
import java.util.Random;


public class BankAccount {
    //instance variables
    private int accountNumber;
    private String name;
    private double balance;

    //static properties
    private static double interest = 0.3;
    private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    //The first constructor receives a name and creates bank account with balance = 0
    public  BankAccount (String name) {
        this(name,0);
    }

    //The second constructor receives name and balance, creates a bank amount and updates balance
    public BankAccount (String name, double balance) {
        this.name = name;
        this.balance = balance;
        Random random = new Random();
        accountNumber = 100000 + random.nextInt(900000);
        accounts.add(this);
    }

    //This method performs deposit operation
    public double deposit(double money) {
        if(money<0){
            return -1;
        }
        else{
            balance+=money;
            return money;
        }
    }

    //This method performs withdraw operation
    public double withdrawMoney(double money) {
        if(money<0 || balance<money){
            return -1;
        }
        else{
            balance -= money;
            return money;
        }
    }

    //This method returns account number
    public int getAccountNumber() {
        return accountNumber;
    }

    //This method returns interest rate
    public static double getInterestRate() {
        int accountSize = accounts.size();
        if(accountSize%5==0){
            int num = accountSize/5;
            double dec = 0.02*num;
            if(interest == (0.30-dec)){
                return interest;
            }
            else{
                interest = interest - 0.02;
                //interest = Math.round(interest);
                return interest;
            }
        }
        return interest;
    }

    //This method performs a transfer operation to a single bank account
    public double transfer(BankAccount destinationBankAccount, double amount) {
        if(amount<0){
            return -1;
        }
        else if(destinationBankAccount == null){
            return -1;
        }
        else if(this.balance<amount){
            return -1;
        }
        else {

            this.withdrawMoney(amount);
            destinationBankAccount.deposit(amount);
            return amount;
        }
    }

    //This method performs a transfer operation to multiple accounts
    public double transfer(BankAccount[] destinationBankAccount, double amount) {
        if (destinationBankAccount == null) {
            return -1;
        }

        else{
            int num = destinationBankAccount.length;
            if (num == 0) {
                return -1;
            } else if (amount < 0) {
                return -1;
            } else if (this.balance < (amount*num)) {
                return -1;
            }
            else {
                double sum = 0;

                for (int i = 0; i < num; i++) {
                    this.withdrawMoney(amount);
                    destinationBankAccount[i].deposit(amount);
                    sum += amount;
                }
                return sum;
            }
        }
    }

}