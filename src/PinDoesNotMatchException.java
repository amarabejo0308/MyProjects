public class PinDoesNotMatchException extends Exception {

    public PinDoesNotMatchException() {
        super("The pin does not match. Please try again.\n");
    }
}
