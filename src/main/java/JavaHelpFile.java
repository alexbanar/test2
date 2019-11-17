import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class JavaHelpFile {
    public static DayOfWeek getDayOfWeek(String str)  {
       LocalDateTime ldt = LocalDateTime.parse(str);
       return ldt.getDayOfWeek();
    }
}
