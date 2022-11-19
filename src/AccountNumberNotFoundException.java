public class AccountNumberNotFoundException extends Exception {
    public AccountNumberNotFoundException() {
        super("The account number does not exist. Please try again.\n");
    }
}
