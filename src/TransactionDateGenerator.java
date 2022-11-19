import java.text.SimpleDateFormat;
import java.util.Date;
public class TransactionDateGenerator {
    
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    
    public static final String getTransactionDate() {
        Date date = new Date();
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
    }
    
}