package org.apache.commons.text;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

/** @deprecated */
@Deprecated
public abstract class StrMatcher {
   private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
   private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
   private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
   private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
   private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
   private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
   private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('"');
   private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
   private static final StrMatcher NONE_MATCHER = new NoMatcher();

   public static StrMatcher charMatcher(char ch) {
      return new CharMatcher(ch);
   }

   public static StrMatcher charSetMatcher(char... chars) {
      if (ArrayUtils.isEmpty(chars)) {
         return NONE_MATCHER;
      } else {
         return (StrMatcher)(chars.length == 1 ? new CharMatcher(chars[0]) : new CharSetMatcher(chars));
      }
   }

   public static StrMatcher charSetMatcher(String chars) {
      if (chars != null && !chars.isEmpty()) {
         return (StrMatcher)(chars.length() == 1 ? new CharMatcher(chars.charAt(0)) : new CharSetMatcher(chars.toCharArray()));
      } else {
         return NONE_MATCHER;
      }
   }

   public static StrMatcher commaMatcher() {
      return COMMA_MATCHER;
   }

   public static StrMatcher doubleQuoteMatcher() {
      return DOUBLE_QUOTE_MATCHER;
   }

   public static StrMatcher noneMatcher() {
      return NONE_MATCHER;
   }

   public static StrMatcher quoteMatcher() {
      return QUOTE_MATCHER;
   }

   public static StrMatcher singleQuoteMatcher() {
      return SINGLE_QUOTE_MATCHER;
   }

   public static StrMatcher spaceMatcher() {
      return SPACE_MATCHER;
   }

   public static StrMatcher splitMatcher() {
      return SPLIT_MATCHER;
   }

   public static StrMatcher stringMatcher(String str) {
      return (StrMatcher)(str != null && !str.isEmpty() ? new StringMatcher(str) : NONE_MATCHER);
   }

   public static StrMatcher tabMatcher() {
      return TAB_MATCHER;
   }

   public static StrMatcher trimMatcher() {
      return TRIM_MATCHER;
   }

   protected StrMatcher() {
   }

   public int isMatch(char[] buffer, int pos) {
      return this.isMatch(buffer, pos, 0, buffer.length);
   }

   public abstract int isMatch(char[] var1, int var2, int var3, int var4);

   private static final class CharMatcher extends StrMatcher {
      private final char ch;

      private CharMatcher(char ch) {
         this.ch = ch;
      }

      public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
         return this.ch == buffer[pos] ? 1 : 0;
      }
   }

   private static final class CharSetMatcher extends StrMatcher {
      private final char[] chars;

      private CharSetMatcher(char[] chars) {
         this.chars = (char[])(([C)chars).clone();
         Arrays.sort(this.chars);
      }

      public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
         return Arrays.binarySearch(this.chars, buffer[pos]) >= 0 ? 1 : 0;
      }
   }

   private static final class NoMatcher extends StrMatcher {
      private NoMatcher() {
      }

      public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
         return 0;
      }
   }

   private static final class StringMatcher extends StrMatcher {
      private final char[] chars;

      private StringMatcher(String str) {
         this.chars = str.toCharArray();
      }

      public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
         int len = this.chars.length;
         if (pos + len > bufferEnd) {
            return 0;
         } else {
            for(int i = 0; i < this.chars.length; ++pos) {
               if (this.chars[i] != buffer[pos]) {
                  return 0;
               }

               ++i;
            }

            return len;
         }
      }

      public String toString() {
         return super.toString() + ' ' + Arrays.toString(this.chars);
      }
   }

   private static final class TrimMatcher extends StrMatcher {
      private TrimMatcher() {
      }

      public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
         return buffer[pos] <= ' ' ? 1 : 0;
      }
   }
}
