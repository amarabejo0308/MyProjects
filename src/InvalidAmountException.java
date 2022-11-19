public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("The amount is not valid. Please try again.\n");
    }
}
