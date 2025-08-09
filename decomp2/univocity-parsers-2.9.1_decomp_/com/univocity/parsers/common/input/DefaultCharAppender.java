package com.univocity.parsers.common.input;

public class DefaultCharAppender implements CharAppender {
   final int whitespaceRangeStart;
   final char[] emptyChars;
   char[] chars;
   int index;
   final String emptyValue;
   int whitespaceCount;

   public DefaultCharAppender(int maxLength, String emptyValue, int whitespaceRangeStart) {
      this.whitespaceRangeStart = whitespaceRangeStart;
      this.chars = new char[maxLength];
      this.emptyValue = emptyValue;
      if (emptyValue == null) {
         this.emptyChars = null;
      } else {
         this.emptyChars = emptyValue.toCharArray();
      }

   }

   public void appendIgnoringPadding(char ch, char padding) {
      this.chars[this.index++] = ch;
      if (ch == padding) {
         ++this.whitespaceCount;
      } else {
         this.whitespaceCount = 0;
      }

   }

   public void appendIgnoringWhitespaceAndPadding(char ch, char padding) {
      this.chars[this.index++] = ch;
      if (ch != padding && (ch > ' ' || this.whitespaceRangeStart >= ch)) {
         this.whitespaceCount = 0;
      } else {
         ++this.whitespaceCount;
      }

   }

   public void appendIgnoringWhitespace(char ch) {
      this.chars[this.index++] = ch;
      if (ch <= ' ' && this.whitespaceRangeStart < ch) {
         ++this.whitespaceCount;
      } else {
         this.whitespaceCount = 0;
      }

   }

   public int indexOf(char ch, int from) {
      int len = this.index - this.whitespaceCount;

      for(int i = from; i < len; ++i) {
         if (this.chars[i] == ch) {
            return i;
         }
      }

      return -1;
   }

   public int indexOfAny(char[] chars, int from) {
      int len = this.index - this.whitespaceCount;

      for(int i = from; i < len; ++i) {
         for(int j = 0; j < chars.length; ++j) {
            if (this.chars[i] == chars[j]) {
               return i;
            }
         }
      }

      return -1;
   }

   public String substring(int from, int length) {
      return new String(this.chars, from, length);
   }

   public void remove(int from, int length) {
      if (length > 0) {
         int srcPos = from + length;
         int len = this.index - length;
         if (srcPos + len > this.index) {
            len -= from;
         }

         System.arraycopy(this.chars, srcPos, this.chars, from, len);
         this.index -= length;
      }

   }

   public void append(char ch) {
      this.chars[this.index++] = ch;
   }

   public final void append(Object o) {
      this.append(String.valueOf(o));
   }

   public final void append(int ch) {
      if (ch < 65536) {
         this.append((char)ch);
      } else {
         int off = ch - 65536;
         this.append((char)((off >>> 10) + '\ud800'));
         this.append((char)((off & 1023) + '\udc00'));
      }

   }

   public final void append(int[] ch) {
      for(int i = 0; i < ch.length; ++i) {
         this.append(ch[i]);
      }

   }

   public String getAndReset() {
      String out = this.emptyValue;
      if (this.index > this.whitespaceCount) {
         out = new String(this.chars, 0, this.index - this.whitespaceCount);
      }

      this.index = 0;
      this.whitespaceCount = 0;
      return out;
   }

   public final String toString() {
      return this.index <= this.whitespaceCount ? this.emptyValue : new String(this.chars, 0, this.index - this.whitespaceCount);
   }

   public final int length() {
      return this.index - this.whitespaceCount;
   }

   public char[] getCharsAndReset() {
      char[] out = this.emptyChars;
      if (this.index > this.whitespaceCount) {
         int length = this.index - this.whitespaceCount;
         out = new char[length];
         System.arraycopy(this.chars, 0, out, 0, length);
      }

      this.index = 0;
      this.whitespaceCount = 0;
      return out;
   }

   public final int whitespaceCount() {
      return this.whitespaceCount;
   }

   public void reset() {
      this.index = 0;
      this.whitespaceCount = 0;
   }

   public void append(DefaultCharAppender appender) {
      System.arraycopy(appender.chars, 0, this.chars, this.index, appender.index - appender.whitespaceCount);
      this.index += appender.index - appender.whitespaceCount;
      appender.reset();
   }

   public final void resetWhitespaceCount() {
      this.whitespaceCount = 0;
   }

   public final char[] getChars() {
      return this.chars;
   }

   public void fill(char ch, int length) {
      for(int i = 0; i < length; ++i) {
         this.chars[this.index++] = ch;
      }

   }

   public void prepend(char ch) {
      System.arraycopy(this.chars, 0, this.chars, 1, this.index);
      this.chars[0] = ch;
      ++this.index;
   }

   public void prepend(char ch1, char ch2) {
      System.arraycopy(this.chars, 0, this.chars, 2, this.index);
      this.chars[0] = ch1;
      this.chars[1] = ch2;
      this.index += 2;
   }

   public void prepend(char[] chars) {
      System.arraycopy(this.chars, 0, this.chars, chars.length, this.index);
      System.arraycopy(chars, 0, this.chars, 0, chars.length);
      this.index += chars.length;
   }

   public final void updateWhitespace() {
      this.whitespaceCount = 0;

      for(int i = this.index - 1; i >= 0 && this.chars[i] <= ' ' && this.whitespaceRangeStart < this.chars[i]; ++this.whitespaceCount) {
         --i;
      }

   }

   public char appendUntil(char ch, CharInput input, char stop) {
      while(ch != stop) {
         this.chars[this.index++] = ch;
         ch = input.nextChar();
      }

      return ch;
   }

   public char appendUntil(char ch, CharInput input, char stop1, char stop2) {
      while(ch != stop1 && ch != stop2) {
         this.chars[this.index++] = ch;
         ch = input.nextChar();
      }

      return ch;
   }

   public char appendUntil(char ch, CharInput input, char stop1, char stop2, char stop3) {
      while(ch != stop1 && ch != stop2 && ch != stop3) {
         this.chars[this.index++] = ch;
         ch = input.nextChar();
      }

      return ch;
   }

   public void append(char[] ch, int from, int length) {
      System.arraycopy(ch, from, this.chars, this.index, length);
      this.index += length;
   }

   public final void append(char[] ch) {
      this.append((char[])ch, 0, ch.length);
   }

   public void append(String string, int from, int to) {
      string.getChars(from, to, this.chars, this.index);
      this.index += to - from;
   }

   public final void append(String string) {
      this.append((String)string, 0, string.length());
   }

   public final char charAt(int i) {
      return this.chars[i];
   }

   public final String subSequence(int from, int to) {
      return new String(this.chars, from, to - from);
   }

   public final void ignore(int count) {
      this.whitespaceCount += count;
   }

   public void delete(int count) {
      this.index -= count;
      if (this.index < 0) {
         this.index = 0;
      }

      this.whitespaceCount = 0;
   }

   public int indexOf(char[] charSequence, int fromIndex) {
      if (charSequence.length == 0) {
         return fromIndex;
      } else if (fromIndex >= this.index) {
         return -1;
      } else {
         char first = charSequence[0];
         int max = this.index - charSequence.length;

         for(int i = fromIndex; i <= max; ++i) {
            if (this.chars[i] != first) {
               do {
                  ++i;
               } while(i <= max && this.chars[i] != first);
            }

            if (i <= max) {
               int j = i + 1;
               int end = j + charSequence.length - 1;

               for(int k = 1; j < end && this.chars[j] == charSequence[k]; ++k) {
                  ++j;
               }

               if (j == end) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   public int indexOf(CharSequence charSequence, int fromIndex) {
      if (charSequence.length() == 0) {
         return fromIndex;
      } else if (fromIndex >= this.index) {
         return -1;
      } else {
         char first = charSequence.charAt(0);
         int max = this.index - charSequence.length();

         for(int i = fromIndex; i <= max; ++i) {
            if (this.chars[i] != first) {
               do {
                  ++i;
               } while(i <= max && this.chars[i] != first);
            }

            if (i <= max) {
               int j = i + 1;
               int end = j + charSequence.length() - 1;

               for(int k = 1; j < end && this.chars[j] == charSequence.charAt(k); ++k) {
                  ++j;
               }

               if (j == end) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   public boolean isEmpty() {
      return this.index > this.whitespaceCount;
   }

   public int lastIndexOf(char ch) {
      for(int x = this.index - this.whitespaceCount - 1; x >= 0; --x) {
         if (this.chars[x] == ch) {
            return x;
         }
      }

      return -1;
   }
}
