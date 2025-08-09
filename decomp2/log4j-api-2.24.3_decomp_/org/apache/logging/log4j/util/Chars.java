package org.apache.logging.log4j.util;

@InternalApi
public final class Chars {
   public static final char CR = '\r';
   public static final char DQUOTE = '"';
   public static final char EQ = '=';
   public static final char LF = '\n';
   public static final char NUL = '\u0000';
   public static final char QUOTE = '\'';
   public static final char SPACE = ' ';
   public static final char TAB = '\t';

   public static char getUpperCaseHex(final int digit) {
      if (digit >= 0 && digit < 16) {
         return digit < 10 ? getNumericalDigit(digit) : getUpperCaseAlphaDigit(digit);
      } else {
         return '\u0000';
      }
   }

   public static char getLowerCaseHex(final int digit) {
      if (digit >= 0 && digit < 16) {
         return digit < 10 ? getNumericalDigit(digit) : getLowerCaseAlphaDigit(digit);
      } else {
         return '\u0000';
      }
   }

   private static char getNumericalDigit(final int digit) {
      return (char)(48 + digit);
   }

   private static char getUpperCaseAlphaDigit(final int digit) {
      return (char)(65 + digit - 10);
   }

   private static char getLowerCaseAlphaDigit(final int digit) {
      return (char)(97 + digit - 10);
   }

   private Chars() {
   }
}
