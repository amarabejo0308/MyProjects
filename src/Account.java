
import java.io.*;

public class Account implements Serializable{

    private String accountNumber;
    private String name;
    private int pin;
    private double accountBalance;

    public Account(String accountNumber, String name, int pin, double accountBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    File directory = new File("AccountRecords");

    {
        directory.mkdir();
    }

    public void writeToFile() {
        try {
            File file = new File("AccountRecords\\Account No. " + accountNumber + ".txt");
            if (file.createNewFile()) {
                System.out.println("Account Created");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
