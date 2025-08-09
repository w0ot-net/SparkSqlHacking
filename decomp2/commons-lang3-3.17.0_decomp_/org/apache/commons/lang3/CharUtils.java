package org.apache.commons.lang3;

import java.util.Objects;
import java.util.function.IntFunction;

public class CharUtils {
   private static final String[] CHAR_STRING_ARRAY = new String[128];
   private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
   public static final char LF = '\n';
   public static final char CR = '\r';
   public static final char NUL = '\u0000';

   public static int compare(char x, char y) {
      return x - y;
   }

   public static boolean isAscii(char ch) {
      return ch < 128;
   }

   public static boolean isAsciiAlpha(char ch) {
      return isAsciiAlphaUpper(ch) || isAsciiAlphaLower(ch);
   }

   public static boolean isAsciiAlphaLower(char ch) {
      return ch >= 'a' && ch <= 'z';
   }

   public static boolean isAsciiAlphanumeric(char ch) {
      return isAsciiAlpha(ch) || isAsciiNumeric(ch);
   }

   public static boolean isAsciiAlphaUpper(char ch) {
      return ch >= 'A' && ch <= 'Z';
   }

   public static boolean isAsciiControl(char ch) {
      return ch < ' ' || ch == 127;
   }

   public static boolean isAsciiNumeric(char ch) {
      return ch >= '0' && ch <= '9';
   }

   public static boolean isAsciiPrintable(char ch) {
      return ch >= ' ' && ch < 127;
   }

   public static char toChar(Character ch) {
      return (Character)Objects.requireNonNull(ch, "ch");
   }

   public static char toChar(Character ch, char defaultValue) {
      return ch != null ? ch : defaultValue;
   }

   public static char toChar(String str) {
      Validate.notEmpty((CharSequence)str, "The String must not be empty");
      return str.charAt(0);
   }

   public static char toChar(String str, char defaultValue) {
      return StringUtils.isEmpty(str) ? defaultValue : str.charAt(0);
   }

   /** @deprecated */
   @Deprecated
   public static Character toCharacterObject(char c) {
      return c;
   }

   public static Character toCharacterObject(String str) {
      return StringUtils.isEmpty(str) ? null : str.charAt(0);
   }

   public static int toIntValue(char ch) {
      if (!isAsciiNumeric(ch)) {
         throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
      } else {
         return ch - 48;
      }
   }

   public static int toIntValue(char ch, int defaultValue) {
      return isAsciiNumeric(ch) ? ch - 48 : defaultValue;
   }

   public static int toIntValue(Character ch) {
      return toIntValue(toChar(ch));
   }

   public static int toIntValue(Character ch, int defaultValue) {
      return ch != null ? toIntValue(ch, defaultValue) : defaultValue;
   }

   public static String toString(char ch) {
      return ch < CHAR_STRING_ARRAY.length ? CHAR_STRING_ARRAY[ch] : String.valueOf(ch);
   }

   public static String toString(Character ch) {
      return ch != null ? toString(ch) : null;
   }

   public static String unicodeEscaped(char ch) {
      return "\\u" + HEX_DIGITS[ch >> 12 & 15] + HEX_DIGITS[ch >> 8 & 15] + HEX_DIGITS[ch >> 4 & 15] + HEX_DIGITS[ch & 15];
   }

   public static String unicodeEscaped(Character ch) {
      return ch != null ? unicodeEscaped(ch) : null;
   }

   static {
      ArrayUtils.setAll(CHAR_STRING_ARRAY, (IntFunction)((i) -> String.valueOf((char)i)));
   }
}
