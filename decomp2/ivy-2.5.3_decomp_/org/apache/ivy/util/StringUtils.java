package org.apache.ivy.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public final class StringUtils {
   private static final char[] SHIFTS = new char[]{'\u0000', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', 'r', 'x', '5', 'O', '`', 'm', 'H', 'l', 'F', '@', 'L', 'C', 't', 'J', 'D', 'W', 'o', '4', 'K', 'w', '1', '"', 'R', 'Q', '_', 'A', 'p', 'V', 'v', 'n', 'z', 'i', ')', '9', 'S', '+', '.', 'f', '(', 'Y', '&', 'g', '-', '2', '*', '{', '[', '#', '}', '7', '6', 'B', '|', '~', ';', '/', '\\', 'G', 's', 'N', 'X', 'k', 'j', '8', '$', 'y', 'u', 'h', 'e', 'd', 'E', 'I', 'c', '?', '^', ']', '\'', '%', '=', '0', ':', 'q', ' ', 'Z', ',', 'b', '<', '3', '!', 'a', '>', 'M', 'T', 'P', 'U', 'ß', 'á', 'Ø', '»', '¦', 'å', '½', 'Þ', '¼', '\u008d', 'ù', '\u0094', 'È', '¸', '\u0088', 'ø', '¾', 'Ç', 'ª', 'µ', 'Ì', '\u008a', 'è', 'Ú', '·', 'ÿ', 'ê', 'Ü', '÷', 'Õ', 'Ë', 'â', 'Á', '®', '¬', 'ä', 'ü', 'Ù', 'É', '\u0083', 'æ', 'Å', 'Ó', '\u0091', 'î', '¡', '³', ' ', 'Ô', 'Ï', 'Ý', 'þ', '\u00ad', 'Ê', '\u0092', 'à', '\u0097', '\u008c', 'Ä', 'Í', '\u0082', '\u0087', '\u0085', '\u008f', 'ö', 'À', '\u009f', 'ô', 'ï', '¹', '¨', '×', '\u0090', '\u008b', '¥', '´', '\u009d', '\u0093', 'º', 'Ö', '°', 'ã', 'ç', 'Û', '©', '¯', '\u009c', 'Î', 'Æ', '\u0081', '¤', '\u0096', 'Ò', '\u009a', '±', '\u0086', '\u007f', '¶', '\u0080', '\u009e', 'Ð', '¢', '\u0084', '§', 'Ñ', '\u0095', 'ñ', '\u0099', 'û', 'í', 'ì', '«', 'Ã', 'ó', 'é', 'ý', 'ð', 'Â', 'ú', '¿', '\u009b', '\u008e', '\u0089', 'õ', 'ë', '£', 'ò', '²', '\u0098'};

   private StringUtils() {
   }

   public static String uncapitalize(String string) {
      if (isNullOrEmpty(string)) {
         return string;
      } else {
         return string.length() == 1 ? string.toLowerCase(Locale.US) : string.substring(0, 1).toLowerCase(Locale.US) + string.substring(1);
      }
   }

   public static String getErrorMessage(Throwable t) {
      if (t == null) {
         return "";
      } else {
         if (t instanceof InvocationTargetException) {
            InvocationTargetException ex = (InvocationTargetException)t;
            t = ex.getTargetException();
         }

         String errMsg = t instanceof RuntimeException ? t.getMessage() : t.toString();
         if (isNullOrEmpty(errMsg) || "null".equals(errMsg)) {
            errMsg = t.getClass().getName() + " at " + t.getStackTrace()[0].toString();
         }

         return errMsg;
      }
   }

   public static String getStackTrace(Throwable e) {
      if (e == null) {
         return "";
      } else {
         StringWriter sw = new StringWriter();
         PrintWriter printWriter = new PrintWriter(sw, true);
         e.printStackTrace(printWriter);
         return sw.getBuffer().toString();
      }
   }

   /** @deprecated */
   @Deprecated
   public static String join(Object[] objs, String sep) {
      for(int i = 0; i < objs.length; ++i) {
         if (!(objs[i] instanceof String)) {
            objs[i] = objs[i].toString();
         }
      }

      return joinArray((String[])objs, sep);
   }

   public static String joinArray(String[] objs, String sep) {
      StringBuilder buf = new StringBuilder();

      for(String obj : objs) {
         buf.append(obj).append(sep);
      }

      if (objs.length > 0) {
         buf.setLength(buf.length() - sep.length());
      }

      return buf.toString();
   }

   public static String[] splitToArray(String list) {
      return list == null ? null : list.trim().split("\\s*,\\s*");
   }

   public static boolean isNullOrEmpty(String s) {
      return s == null || s.trim().isEmpty();
   }

   public static void assertNotNullNorEmpty(String value, String errorMessage) {
      if (isNullOrEmpty(value)) {
         throw new IllegalArgumentException(errorMessage);
      }
   }

   /** @deprecated */
   @Deprecated
   public static void assertNotNullNotEmpty(String value, String errorMessage) {
      assertNotNullNorEmpty(value, errorMessage);
   }

   public static final String encrypt(String str) {
      if (str == null) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();

         for(char c : str.toCharArray()) {
            if (c >= SHIFTS.length) {
               throw new IllegalArgumentException("encrypt method can only be used with simple characters. '" + c + "' not allowed");
            }

            buf.append(SHIFTS[c]);
         }

         return buf.toString();
      }
   }

   public static final String decrypt(String str) {
      if (str == null) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();

         for(char c : str.toCharArray()) {
            buf.append(decrypt(c));
         }

         return buf.toString();
      }
   }

   private static char decrypt(char c) {
      for(char i = 0; i < SHIFTS.length; ++i) {
         if (SHIFTS[i] == c) {
            return i;
         }
      }

      throw new IllegalArgumentException("Impossible to decrypt '" + c + "'. Unhandled character.");
   }

   public static String repeat(String str, int count) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < count; ++i) {
         sb.append(str);
      }

      return sb.toString();
   }
}
