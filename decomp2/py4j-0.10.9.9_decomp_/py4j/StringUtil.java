package py4j;

public class StringUtil {
   public static final char ESCAPE_CHAR = '\\';

   public static String escape(String original) {
      return original != null ? original.replace("\\", "\\\\").replace("\r", "\\r").replace("\n", "\\n") : null;
   }

   public static String unescape(String escaped) {
      boolean escaping = false;
      StringBuilder newString = new StringBuilder();

      for(char c : escaped.toCharArray()) {
         if (!escaping) {
            if (c == '\\') {
               escaping = true;
            } else {
               newString.append(c);
            }
         } else {
            if (c == 'n') {
               newString.append('\n');
            } else if (c == 'r') {
               newString.append('\r');
            } else {
               newString.append(c);
            }

            escaping = false;
         }
      }

      return newString.toString();
   }
}
