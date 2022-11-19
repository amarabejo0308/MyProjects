
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Create initial menu will display [A] Open An Account, [B] Account Login, and
        // [C] Cancel
        System.out.println("[A] Open An Account");
        System.out.println("[B] Account Login");
        System.out.println("[C] Cancel");
        System.out.print("Please select an option: ");
        Scanner input = new Scanner(System.in);
        String option = input.nextLine();

        // if user select [C] Cancel, the program will terminate
        if (option.equalsIgnoreCase("C")) {
            System.out.println("Thank you for using our service. Goodbye!");
            System.exit(0);
        }

        if (option.equalsIgnoreCase("A")) {
            System.out.print("Please enter your name: ");
            String name = input.nextLine();
            int pin = 0;
            int confirmPin = 0;
            int count = 0;
            while (count < 3) {
                try {
                    System.out.print("Please enter your 6-digit pin: ");
                    pin = input.nextInt();
                    System.out.print("Please confirm your 6-digit pin: ");
                    confirmPin = input.nextInt();
                    if (pin != confirmPin) {
                        PinDoesNotMatchException e = new PinDoesNotMatchException();
                        throw e;
                    } else if (String.valueOf(pin).length() != 6) {
                        PinWrongSpecificationException e = new PinWrongSpecificationException();
                        throw e;
                    } else {
                        break;
                    }
                } catch (PinDoesNotMatchException e) {
                    System.out.println("\nPinDoesNotMatchException: \n" + e.getMessage());

                    count++;
                } catch (PinWrongSpecificationException e) {
                    System.out.println("\nPinWrongSpecificationException: \n" + e.getMessage());
                    count++;
                }
            }
            if (count >= 3) {
                System.out.println("\nYou have entered the wrong pin three times. The program will now terminate.");
            } else {
                String accountNumber = AccountNumberGenerator.generateAccountNumber();
                Account account = new Account(accountNumber, name, pin, 0);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("AccountRecords\\Account No. " + accountNumber + ".txt"));
                writer.write("Account Number: " + accountNumber);
                writer.newLine();
                writer.write("Name: " + name);
                writer.newLine();
                writer.write("Pin: " + pin);
                writer.newLine();
                writer.write("Account Balance: " + 0);
                writer.close();
                System.out.println("Your account number is: " + accountNumber);
            }
        }

        if (option.equalsIgnoreCase("B")) {
            System.out.print("Please enter your account number: ");
            String accountNumber = input.nextLine();
            File file = new File("AccountRecords\\Account No. " + accountNumber + ".txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                String name = "";
                int pin = 0;
                int balance = 0;
                while (line != null) {
                    String[] str = line.split(": ");
                    if (str[0].equals("Account Number")) {
                        accountNumber = str[1];
                    } else if (str[0].equals("Name")) {
                        name = str[1];
                    } else if (str[0].equals("Pin")) {
                        pin = Integer.parseInt(str[1]);
                    } else if (str[0].equals("Account Balance")) {
                        balance = Integer.parseInt(str[1]);
                    }
                    line = reader.readLine();
                }
                reader.close();
                Account account = new Account(accountNumber, name, pin, balance);
                int count = 0;
                while (count < 3) {
                    try {
                        System.out.print("Please enter your 6-digit pin: ");
                        pin = input.nextInt();
                        if (pin != account.getPin()) {
                            PinDoesNotMatchException e = new PinDoesNotMatchException();
                            throw e;
                        } else {
                            break;
                        }
                    } catch (PinDoesNotMatchException e) {
                        System.out.println("\nPinDoesNotMatchException: \n" + e.getMessage());
                        count++;
                    }
                }
                if (count >= 3) {
                    System.out.println("\nYou have entered the wrong pin three times. The program will now terminate.");
                } else {
                    System.out.println("Welcome, " + name + "!");
                    System.out.println("[A] Balance Inquiry");
                    System.out.println("[B] Deposit Money");
                    System.out.println("[C] Withdraw Money");
                    System.out.println("[D] Show Transaction History");
                    System.out.println("[E] Cancel");
                    System.out.print("Please select an option: ");
                    String option2 = input.next();
                    if (option2.equalsIgnoreCase("A")) {
                        System.out.println("Current Available Balance: PhP " + account.getAccountBalance() + ".00");
                    } else if (option2.equalsIgnoreCase("B")) {

                    } //option [B] Deposit Money
                    else if (option2.equalsIgnoreCase("B")) {
                        System.out.print("How much money do you want to deposit? ");
                        int amount = input.nextInt();
                        try {
                            if (amount % 10 != 0) {
                                InvalidAmountException e = new InvalidAmountException();
                                throw e;
                            } else {
                                System.out.print("Please enter your 6-digit pin: ");
                                pin = input.nextInt();
                                if (pin != account.getPin()) {
                                    PinDoesNotMatchException e = new PinDoesNotMatchException();
                                    throw e;
                                } else {
                                    account.setAccountBalance(account.getAccountBalance() + amount);
                                    System.out.println("Current Available Balance: PhP " + account.getAccountBalance() + ".00");
                                    FileWriter writer = new FileWriter(file);
                                    writer.write("Account Number: " + account.getAccountNumber() + " \r \n");
                                    writer.write("Name: " + account.getName() + " \r \n");
                                    writer.write("Pin: " + account.getPin() + " \r \n");
                                    writer.write("Account Balance: " + account.getAccountBalance() + " \r \n");
                                    writer.close();
                                    File transactionFile = new File("AccountRecords\\Account No. " + account.getAccountNumber() + "-transaction.txt");
                                    if (transactionFile.exists()) {
                                        BufferedReader reader2 = new BufferedReader(new FileReader(transactionFile));
                                        String line2 = reader2.readLine();
                                        String[] str = line2.split(": ");
                                        int transactionCount = Integer.parseInt(str[1]);
                                        reader2.close();
                                        FileWriter writer2 = new FileWriter(transactionFile);
                                        writer2.write("Transaction Count: " + (transactionCount + 1) + " \r \n");
                                        writer2.write("Transaction " + (transactionCount + 1) + ": " + amount + " \r \n");
                                        writer2.close();
                                    } else {
                                        FileWriter writer2 = new FileWriter(transactionFile);
                                        writer2.write("Transaction Count: 1 \r \n");
                                        writer2.write("Transaction 1: " + amount + " \r \n");
                                        writer2.close();
                                    }

                                }
                            }
                        } catch (InvalidAmountException e) {
                            System.out.println("\nInvalidAmountException: \n" + e.getMessage());
                        } catch (PinDoesNotMatchException e) {
                            System.out.println("\nPinDoesNotMatchException: \n" + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("Account does not exist.");
            }
        }
    }
}
