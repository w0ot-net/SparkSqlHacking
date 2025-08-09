package jodd.util;

import java.io.UnsupportedEncodingException;
import jodd.JoddCore;

public class CharUtil {
   public static char toChar(byte b) {
      return (char)(b & 255);
   }

   public static byte[] toSimpleByteArray(char[] carr) {
      byte[] barr = new byte[carr.length];

      for(int i = 0; i < carr.length; ++i) {
         barr[i] = (byte)carr[i];
      }

      return barr;
   }

   public static byte[] toSimpleByteArray(CharSequence charSequence) {
      byte[] barr = new byte[charSequence.length()];

      for(int i = 0; i < barr.length; ++i) {
         barr[i] = (byte)charSequence.charAt(i);
      }

      return barr;
   }

   public static char[] toSimpleCharArray(byte[] barr) {
      char[] carr = new char[barr.length];

      for(int i = 0; i < barr.length; ++i) {
         carr[i] = (char)(barr[i] & 255);
      }

      return carr;
   }

   public static int toAscii(char c) {
      return c <= 255 ? c : 63;
   }

   public static byte[] toAsciiByteArray(char[] carr) {
      byte[] barr = new byte[carr.length];

      for(int i = 0; i < carr.length; ++i) {
         barr[i] = (byte)(carr[i] <= 255 ? carr[i] : 63);
      }

      return barr;
   }

   public static byte[] toAsciiByteArray(CharSequence charSequence) {
      byte[] barr = new byte[charSequence.length()];

      for(int i = 0; i < barr.length; ++i) {
         char c = charSequence.charAt(i);
         barr[i] = (byte)(c <= 255 ? c : 63);
      }

      return barr;
   }

   public static byte[] toRawByteArray(char[] carr) {
      byte[] barr = new byte[carr.length << 1];
      int i = 0;

      for(int bpos = 0; i < carr.length; ++i) {
         char c = carr[i];
         barr[bpos++] = (byte)((c & '\uff00') >> 8);
         barr[bpos++] = (byte)(c & 255);
      }

      return barr;
   }

   public static char[] toRawCharArray(byte[] barr) {
      int carrLen = barr.length >> 1;
      if (carrLen << 1 < barr.length) {
         ++carrLen;
      }

      char[] carr = new char[carrLen];
      int i = 0;

      char c;
      for(int j = 0; i < barr.length; carr[j++] = c) {
         c = (char)(barr[i] << 8);
         ++i;
         if (i != barr.length) {
            c = (char)(c + (barr[i] & 255));
            ++i;
         }
      }

      return carr;
   }

   public static byte[] toByteArray(char[] carr) throws UnsupportedEncodingException {
      return (new String(carr)).getBytes(JoddCore.encoding);
   }

   public static byte[] toByteArray(char[] carr, String charset) throws UnsupportedEncodingException {
      return (new String(carr)).getBytes(charset);
   }

   public static char[] toCharArray(byte[] barr) throws UnsupportedEncodingException {
      return (new String(barr, JoddCore.encoding)).toCharArray();
   }

   public static char[] toCharArray(byte[] barr, String charset) throws UnsupportedEncodingException {
      return (new String(barr, charset)).toCharArray();
   }

   public static boolean equalsOne(char c, char[] match) {
      for(char aMatch : match) {
         if (c == aMatch) {
            return true;
         }
      }

      return false;
   }

   public static int findFirstEqual(char[] source, int index, char[] match) {
      for(int i = index; i < source.length; ++i) {
         if (equalsOne(source[i], match)) {
            return i;
         }
      }

      return -1;
   }

   public static int findFirstEqual(char[] source, int index, char match) {
      for(int i = index; i < source.length; ++i) {
         if (source[i] == match) {
            return i;
         }
      }

      return -1;
   }

   public static int findFirstDiff(char[] source, int index, char[] match) {
      for(int i = index; i < source.length; ++i) {
         if (!equalsOne(source[i], match)) {
            return i;
         }
      }

      return -1;
   }

   public static int findFirstDiff(char[] source, int index, char match) {
      for(int i = index; i < source.length; ++i) {
         if (source[i] != match) {
            return i;
         }
      }

      return -1;
   }

   public static boolean isWhitespace(char c) {
      return c <= ' ';
   }

   public static boolean isLowercaseAlpha(char c) {
      return c >= 'a' && c <= 'z';
   }

   public static boolean isUppercaseAlpha(char c) {
      return c >= 'A' && c <= 'Z';
   }

   public static boolean isAlphaOrDigit(char c) {
      return isDigit(c) || isAlpha(c);
   }

   public static boolean isWordChar(char c) {
      return isDigit(c) || isAlpha(c) || c == '_';
   }

   public static boolean isPropertyNameChar(char c) {
      return isDigit(c) || isAlpha(c) || c == '_' || c == '.' || c == '[' || c == ']';
   }

   public static boolean isAlpha(char c) {
      return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
   }

   public static boolean isDigit(char c) {
      return c >= '0' && c <= '9';
   }

   public static boolean isGenericDelimiter(int c) {
      return c == 58 || c == 47 || c == 63 || c == 35 || c == 91 || c == 93 || c == 64;
   }

   protected static boolean isSubDelimiter(int c) {
      return c == 33 || c == 36 || c == 38 || c == 39 || c == 40 || c == 41 || c == 42 || c == 43 || c == 44 || c == 59 || c == 61;
   }

   protected static boolean isReserved(char c) {
      return isGenericDelimiter(c) || isReserved(c);
   }

   protected static boolean isUnreserved(char c) {
      return isAlpha(c) || isDigit(c) || c == '-' || c == '.' || c == '_' || c == '~';
   }

   protected static boolean isPchar(char c) {
      return isUnreserved(c) || isSubDelimiter(c) || c == ':' || c == '@';
   }

   public static char toUpperAscii(char c) {
      if (isLowercaseAlpha(c)) {
         c = (char)(c - 32);
      }

      return c;
   }

   public static char toLowerAscii(char c) {
      if (isUppercaseAlpha(c)) {
         c = (char)(c + 32);
      }

      return c;
   }
}
