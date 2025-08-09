package jodd.util;

public class HtmlEncoder {
   protected static final char[][] ATTR = new char[64][];
   protected static final char[][] TEXT = new char[64][];
   protected static final char[][] BLOCK = new char[64][];

   public static String attribute(String value) {
      return encode(value, ATTR);
   }

   public static String text(String text) {
      return encode(text, TEXT);
   }

   private static String encode(String text, char[][] array) {
      int len;
      if (text != null && (len = text.length()) != 0) {
         StringBuilder buffer = new StringBuilder(len + (len >> 2));

         for(int i = 0; i < len; ++i) {
            char c = text.charAt(i);
            if (c < '@') {
               buffer.append(array[c]);
            } else {
               buffer.append(c);
            }
         }

         return buffer.toString();
      } else {
         return "";
      }
   }

   public static String block(String text) {
      int len;
      if (text != null && (len = text.length()) != 0) {
         StringBuilder buffer = new StringBuilder(len + (len >> 2));
         char prev = 0;

         char c;
         for(int i = 0; i < len; prev = c) {
            c = text.charAt(i);
            if (c != '\n' || prev != '\r') {
               if (c < '@') {
                  buffer.append(BLOCK[c]);
               } else {
                  buffer.append(c);
               }
            }

            ++i;
         }

         return buffer.toString();
      } else {
         return "";
      }
   }

   public static String strict(String text) {
      int len;
      if (text != null && (len = text.length()) != 0) {
         StringBuilder buffer = new StringBuilder(len + (len >> 2));
         char prev = 0;
         boolean prevSpace = false;

         char c;
         for(int i = 0; i < len; prev = c) {
            c = text.charAt(i);
            if (c == ' ') {
               if (prev != ' ') {
                  prevSpace = false;
               }

               if (!prevSpace) {
                  buffer.append(' ');
               } else {
                  buffer.append("&nbsp;");
               }

               prevSpace = !prevSpace;
            } else if (c != '\n' || prev != '\r') {
               if (c < '@') {
                  buffer.append(BLOCK[c]);
               } else {
                  buffer.append(c);
               }
            }

            ++i;
         }

         return buffer.toString();
      } else {
         return "";
      }
   }

   static {
      for(int i = 0; i < 64; ++i) {
         TEXT[i] = new char[]{(char)i};
      }

      TEXT[39] = "&#039;".toCharArray();
      TEXT[34] = "&quot;".toCharArray();
      TEXT[38] = "&amp;".toCharArray();
      TEXT[60] = "&lt;".toCharArray();
      TEXT[62] = "&gt;".toCharArray();
      System.arraycopy(TEXT, 0, BLOCK, 0, 64);
      BLOCK[10] = "<br/>".toCharArray();
      BLOCK[13] = "<br/>".toCharArray();
      System.arraycopy(TEXT, 0, ATTR, 0, 64);
      ATTR[39] = "'".toCharArray();
   }
}
