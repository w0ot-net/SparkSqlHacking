package jodd.datetime;

import java.util.Locale;
import java.util.TimeZone;
import jodd.datetime.format.Iso8601JdtFormatter;
import jodd.datetime.format.JdtFormatter;

public class JDateTimeDefault {
   public static boolean monthFix = true;
   public static TimeZone timeZone = null;
   public static Locale locale = null;
   public static String format = "YYYY-MM-DD hh:mm:ss.mss";
   public static JdtFormatter formatter = new Iso8601JdtFormatter();
   public static int firstDayOfWeek = 1;
   public static int mustHaveDayOfFirstWeek = 4;
   public static int minDaysInFirstWeek = 4;
   public static boolean trackDST = false;
}
