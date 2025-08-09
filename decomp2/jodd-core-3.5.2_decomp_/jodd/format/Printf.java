package jodd.format;

public class Printf {
   public static String str(String format, byte value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, char value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, short value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, int value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, long value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, float value) {
      return (new PrintfFormat(format)).form((double)value);
   }

   public static String str(String format, double value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, boolean value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, String value) {
      return (new PrintfFormat(format)).form(value);
   }

   public static String str(String format, Object param) {
      return (new PrintfFormat(format)).form(param);
   }

   public static String str(String format, Object... params) {
      PrintfFormat pf = new PrintfFormat();

      for(Object param : params) {
         pf.reinit(format);
         format = pf.form(param);
      }

      return format;
   }
}
