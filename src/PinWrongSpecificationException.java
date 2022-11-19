    public class PinWrongSpecificationException extends Exception {

        public PinWrongSpecificationException() {
            super("The pin is not 6 digits. Please try again.\n");
        }
    }
