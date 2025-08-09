package javolution.text;

import java.io.IOException;
import javolution.lang.MathLib;

public final class TypeFormat {
   private static final CharSequence TRUE = j2meToCharSeq("true");
   private static final CharSequence FALSE = j2meToCharSeq("false");

   private TypeFormat() {
   }

   public static boolean parseBoolean(CharSequence csq) {
      return parseBoolean(csq, (Cursor)null);
   }

   public static boolean parseBoolean(String str) {
      return parseBoolean(j2meToCharSeq(str));
   }

   public static boolean parseBoolean(CharSequence csq, Cursor cursor) {
      int start = cursor != null ? cursor.getIndex() : 0;
      int end = csq.length();
      if (end < start + 5 || csq.charAt(start) != 'f' && csq.charAt(start) != 'F') {
         if (end >= start + 4 && (csq.charAt(start) == 't' || csq.charAt(start) == 'T')) {
            ++start;
            if (csq.charAt(start) == 'r' || csq.charAt(start) == 'R') {
               ++start;
               if (csq.charAt(start) == 'u' || csq.charAt(start) == 'U') {
                  ++start;
                  if (csq.charAt(start) == 'e' || csq.charAt(start) == 'E') {
                     increment(cursor, 4, end, csq);
                     return true;
                  }
               }
            }
         }
      } else {
         ++start;
         if (csq.charAt(start) == 'a' || csq.charAt(start) == 'A') {
            ++start;
            if (csq.charAt(start) == 'l' || csq.charAt(start) == 'L') {
               ++start;
               if (csq.charAt(start) == 's' || csq.charAt(start) == 'S') {
                  ++start;
                  if (csq.charAt(start) == 'e' || csq.charAt(start) == 'E') {
                     increment(cursor, 5, end, csq);
                     return false;
                  }
               }
            }
         }
      }

      throw new IllegalArgumentException("Invalid boolean representation");
   }

   public static byte parseByte(CharSequence csq) {
      return parseByte(csq, 10);
   }

   public static byte parseByte(CharSequence csq, int radix) {
      int i = parseInt(csq, radix);
      if (i >= -128 && i <= 127) {
         return (byte)i;
      } else {
         throw new NumberFormatException("Overflow");
      }
   }

   public static byte parseByte(CharSequence csq, int radix, Cursor cursor) {
      int i = parseInt(csq, radix, cursor);
      if (i >= -128 && i <= 127) {
         return (byte)i;
      } else {
         throw new NumberFormatException("Overflow");
      }
   }

   public static short parseShort(CharSequence csq) {
      return parseShort(csq, 10);
   }

   public static short parseShort(CharSequence csq, int radix) {
      int i = parseInt(csq, radix);
      if (i >= -32768 && i <= 32767) {
         return (short)i;
      } else {
         throw new NumberFormatException("Overflow");
      }
   }

   public static short parseShort(CharSequence csq, int radix, Cursor cursor) {
      int i = parseInt(csq, radix, cursor);
      if (i >= -32768 && i <= 32767) {
         return (short)i;
      } else {
         throw new NumberFormatException("Overflow");
      }
   }

   public static int parseInt(CharSequence csq) {
      return parseInt((CharSequence)csq, 10);
   }

   public static int parseInt(String str) {
      return parseInt(j2meToCharSeq(str));
   }

   public static int parseInt(CharSequence csq, int radix) {
      return parseInt(csq, radix, (Cursor)null);
   }

   public static int parseInt(String str, int radix) {
      return parseInt(j2meToCharSeq(str), radix);
   }

   public static int parseInt(CharSequence csq, int radix, Cursor cursor) {
      int start = cursor != null ? cursor.getIndex() : 0;
      int end = csq.length();
      boolean isNegative = false;
      int result = 0;

      int i;
      for(i = start; i < end; ++i) {
         char c = csq.charAt(i);
         int digit = c <= '9' ? c - 48 : (c <= 'Z' && c >= 'A' ? c - 65 + 10 : (c <= 'z' && c >= 'a' ? c - 97 + 10 : -1));
         if (digit >= 0 && digit < radix) {
            int newResult = result * radix - digit;
            if (newResult > result) {
               throw new NumberFormatException("Overflow parsing " + csq.subSequence(start, end));
            }

            result = newResult;
         } else if (c == '-' && i == start) {
            isNegative = true;
         } else if (c != '+' || i != start) {
            break;
         }
      }

      if (result != 0 || end != 0 && csq.charAt(i - 1) == '0') {
         if (result == Integer.MIN_VALUE && !isNegative) {
            throw new NumberFormatException("Overflow parsing " + csq.subSequence(start, end));
         } else {
            increment(cursor, i - start, end, csq);
            return isNegative ? result : -result;
         }
      } else {
         throw new NumberFormatException("Invalid integer representation for " + csq.subSequence(start, end));
      }
   }

   public static long parseLong(CharSequence csq) {
      return parseLong((CharSequence)csq, 10);
   }

   public static long parseLong(String str) {
      return parseLong((CharSequence)j2meToCharSeq(str), 10);
   }

   public static long parseLong(CharSequence csq, int radix) {
      return parseLong(csq, radix, (Cursor)null);
   }

   public static long parseLong(String str, int radix) {
      return parseLong(j2meToCharSeq(str), radix);
   }

   public static long parseLong(CharSequence csq, int radix, Cursor cursor) {
      int start = cursor != null ? cursor.getIndex() : 0;
      int end = csq.length();
      boolean isNegative = false;
      long result = 0L;

      int i;
      for(i = start; i < end; ++i) {
         char c = csq.charAt(i);
         int digit = c <= '9' ? c - 48 : (c <= 'Z' && c >= 'A' ? c - 65 + 10 : (c <= 'z' && c >= 'a' ? c - 97 + 10 : -1));
         if (digit >= 0 && digit < radix) {
            long newResult = result * (long)radix - (long)digit;
            if (newResult > result) {
               throw new NumberFormatException("Overflow parsing " + csq.subSequence(start, end));
            }

            result = newResult;
         } else if (c == '-' && i == start) {
            isNegative = true;
         } else if (c != '+' || i != start) {
            break;
         }
      }

      if (result != 0L || end != 0 && csq.charAt(i - 1) == '0') {
         if (result == Long.MIN_VALUE && !isNegative) {
            throw new NumberFormatException("Overflow parsing " + csq.subSequence(start, end));
         } else {
            increment(cursor, i - start, end, csq);
            return isNegative ? result : -result;
         }
      } else {
         throw new NumberFormatException("Invalid integer representation for " + csq.subSequence(start, end));
      }
   }

   public static float parseFloat(CharSequence csq) {
      return (float)parseDouble(csq);
   }

   public static float parseFloat(String str) {
      return parseFloat(j2meToCharSeq(str));
   }

   public static float parseFloat(CharSequence csq, Cursor cursor) {
      return (float)parseDouble(csq, cursor);
   }

   public static double parseDouble(CharSequence csq) throws NumberFormatException {
      return parseDouble(csq, (Cursor)null);
   }

   public static double parseDouble(String str) {
      return parseDouble(j2meToCharSeq(str));
   }

   public static double parseDouble(CharSequence csq, Cursor cursor) throws NumberFormatException {
      int start = cursor != null ? cursor.getIndex() : 0;
      int end = csq.length();
      int i = start;
      char c = csq.charAt(start);
      if (c == 'N' && match("NaN", csq, start, end)) {
         increment(cursor, 3, end, csq);
         return Double.NaN;
      } else {
         boolean isNegative = c == '-';
         if (isNegative || c == '+') {
            i = start + 1;
            if (i < end) {
               c = csq.charAt(i);
            }
         }

         if (c == 'I' && match("Infinity", csq, i, end)) {
            increment(cursor, i + 8 - start, end, csq);
            return isNegative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
         } else if ((c < '0' || c > '9') && c != '.') {
            throw new NumberFormatException("Digit or '.' required");
         } else {
            long decimal = 0L;
            int decimalPoint = -1;

            while(true) {
               int digit = c - 48;
               if (digit >= 0 && digit < 10) {
                  long tmp = decimal * 10L + (long)digit;
                  if (tmp < decimal) {
                     throw new NumberFormatException("Too many digits - Overflow");
                  }

                  decimal = tmp;
               } else {
                  if (c != '.' || decimalPoint >= 0) {
                     break;
                  }

                  decimalPoint = i;
               }

               ++i;
               if (i >= end) {
                  break;
               }

               c = csq.charAt(i);
            }

            if (isNegative) {
               decimal = -decimal;
            }

            int fractionLength = decimalPoint >= 0 ? i - decimalPoint - 1 : 0;
            int exp = 0;
            if (i < end && (c == 'E' || c == 'e')) {
               ++i;
               c = csq.charAt(i);
               boolean isNegativeExp = c == '-';
               if (isNegativeExp || c == '+') {
                  ++i;
                  if (i < end) {
                     c = csq.charAt(i);
                  }
               }

               if (c < '0' || c > '9') {
                  throw new NumberFormatException("Invalid exponent");
               }

               while(true) {
                  int digit = c - 48;
                  if (digit < 0 || digit >= 10) {
                     break;
                  }

                  int tmp = exp * 10 + digit;
                  if (tmp < exp) {
                     throw new NumberFormatException("Exponent Overflow");
                  }

                  exp = tmp;
                  ++i;
                  if (i >= end) {
                     break;
                  }

                  c = csq.charAt(i);
               }

               if (isNegativeExp) {
                  exp = -exp;
               }
            }

            increment(cursor, i - start, end, csq);
            return MathLib.toDoublePow10(decimal, exp - fractionLength);
         }
      }
   }

   static boolean match(String str, CharSequence csq, int start, int length) {
      for(int i = 0; i < str.length(); ++i) {
         if (start + i >= length || csq.charAt(start + i) != str.charAt(i)) {
            return false;
         }
      }

      return true;
   }

   static boolean match(String str, String csq, int start, int length) {
      for(int i = 0; i < str.length(); ++i) {
         if (start + i >= length || csq.charAt(start + i) != str.charAt(i)) {
            return false;
         }
      }

      return true;
   }

   public static Appendable format(boolean b, Appendable a) throws IOException {
      return b ? a.append(TRUE) : a.append(FALSE);
   }

   public static Appendable format(int i, Appendable a) throws IOException {
      if (a instanceof TextBuilder) {
         return ((TextBuilder)a).append(i);
      } else {
         TextBuilder tb = TextBuilder.newInstance();

         Appendable var3;
         try {
            tb.append(i);
            var3 = a.append(tb);
         } finally {
            TextBuilder.recycle(tb);
         }

         return var3;
      }
   }

   public static Appendable format(int i, int radix, Appendable a) throws IOException {
      if (a instanceof TextBuilder) {
         return ((TextBuilder)a).append(i, radix);
      } else {
         TextBuilder tb = TextBuilder.newInstance();

         Appendable var4;
         try {
            tb.append(i, radix);
            var4 = a.append(tb);
         } finally {
            TextBuilder.recycle(tb);
         }

         return var4;
      }
   }

   public static Appendable format(long l, Appendable a) throws IOException {
      if (a instanceof TextBuilder) {
         return ((TextBuilder)a).append(l);
      } else {
         TextBuilder tb = TextBuilder.newInstance();

         Appendable var4;
         try {
            tb.append(l);
            var4 = a.append(tb);
         } finally {
            TextBuilder.recycle(tb);
         }

         return var4;
      }
   }

   public static Appendable format(long l, int radix, Appendable a) throws IOException {
      if (a instanceof TextBuilder) {
         return ((TextBuilder)a).append(l, radix);
      } else {
         TextBuilder tb = TextBuilder.newInstance();

         Appendable var5;
         try {
            tb.append(l, radix);
            var5 = a.append(tb);
         } finally {
            TextBuilder.recycle(tb);
         }

         return var5;
      }
   }

   public static Appendable format(float f, Appendable a) throws IOException {
      return format((double)f, 10, (double)MathLib.abs(f) >= (double)1.0E7F || (double)MathLib.abs(f) < 0.001, false, a);
   }

   public static Appendable format(double d, Appendable a) throws IOException {
      return format(d, -1, MathLib.abs(d) >= (double)1.0E7F || MathLib.abs(d) < 0.001, false, a);
   }

   public static Appendable format(double d, int digits, boolean scientific, boolean showZero, Appendable a) throws IOException {
      if (a instanceof TextBuilder) {
         return ((TextBuilder)a).append(d, digits, scientific, showZero);
      } else {
         TextBuilder tb = TextBuilder.newInstance();

         Appendable var7;
         try {
            tb.append(d, digits, scientific, showZero);
            var7 = a.append(tb);
         } finally {
            TextBuilder.recycle(tb);
         }

         return var7;
      }
   }

   private static void increment(Cursor cursor, int inc, int endIndex, CharSequence csq) throws NumberFormatException {
      if (cursor != null) {
         cursor.increment(inc);
      } else if (inc != endIndex) {
         throw new NumberFormatException("Extraneous character: '" + csq.charAt(inc) + "'");
      }

   }

   static CharSequence j2meToCharSeq(Object str) {
      return (CharSequence)str;
   }
}
