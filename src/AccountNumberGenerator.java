
public class AccountNumberGenerator {
    
    private static final Long MIN = 1000000000L;
    private static final Long MAX = 9999999999L;
    
    public static final String generateAccountNumber() {
        Long value = Double.valueOf(Math.random() * (MAX - MIN + 1) + 
MIN).longValue();
        return value.toString();
    }

    
}
