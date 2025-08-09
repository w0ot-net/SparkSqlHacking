package com.univocity.parsers.common.input;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.TextParsingException;
import java.util.Arrays;

public class ExpandingCharAppender extends DefaultCharAppender {
   private static final int MAX_ARRAY_LENGTH = 2147483639;

   public ExpandingCharAppender(String emptyValue, int whitespaceRangeStart) {
      this(8192, emptyValue, whitespaceRangeStart);
   }

   public ExpandingCharAppender(int initialBufferLength, String emptyValue, int whitespaceRangeStart) {
      super(initialBufferLength, emptyValue, whitespaceRangeStart);
   }

   public void appendIgnoringWhitespace(char ch) {
      try {
         super.appendIgnoringWhitespace(ch);
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.expandAndRetry();
         super.appendIgnoringWhitespace(ch);
      }

   }

   public void appendIgnoringPadding(char ch, char padding) {
      try {
         super.appendIgnoringPadding(ch, padding);
      } catch (ArrayIndexOutOfBoundsException var4) {
         this.expandAndRetry();
         super.appendIgnoringPadding(ch, padding);
      }

   }

   public void appendIgnoringWhitespaceAndPadding(char ch, char padding) {
      try {
         super.appendIgnoringWhitespaceAndPadding(ch, padding);
      } catch (ArrayIndexOutOfBoundsException var4) {
         this.expandAndRetry();
         super.appendIgnoringWhitespaceAndPadding(ch, padding);
      }

   }

   public void append(char ch) {
      try {
         super.append(ch);
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.expandAndRetry();
         super.append(ch);
      }

   }

   public final void fill(char ch, int length) {
      try {
         super.fill(ch, length);
      } catch (ArrayIndexOutOfBoundsException var4) {
         this.expandAndRetry();
         super.fill(ch, length);
      }

   }

   final void expandAndRetry() {
      this.expand();
      --this.index;
   }

   private void expand(int additionalLength, double factor) {
      if (this.chars.length == 2147483639) {
         throw new TextParsingException((ParsingContext)null, "Can't expand internal appender array to over 2147483639 characters in length.");
      } else {
         this.chars = Arrays.copyOf(this.chars, (int)Math.min((double)(this.index + additionalLength) * factor, 2.147483639E9));
      }
   }

   final void expand() {
      this.expand(0, (double)2.0F);
   }

   final void expand(int additionalLength) {
      this.expand(additionalLength, (double)1.5F);
   }

   public final void prepend(char ch) {
      try {
         super.prepend(ch);
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.expand();
         super.prepend(ch);
      }

   }

   public final void prepend(char ch1, char ch2) {
      try {
         super.prepend(ch1, ch2);
      } catch (ArrayIndexOutOfBoundsException var4) {
         this.expand(2);
         super.prepend(ch1, ch2);
      }

   }

   public final void prepend(char[] chars) {
      try {
         super.prepend(chars);
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.expand(chars.length);
         super.prepend(chars);
      }

   }

   public final void append(DefaultCharAppender appender) {
      try {
         super.append(appender);
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.expand(appender.index);
         this.append(appender);
      }

   }

   public final char appendUntil(char ch, CharInput input, char stop) {
      try {
         return super.appendUntil(ch, input, stop);
      } catch (ArrayIndexOutOfBoundsException var5) {
         this.expandAndRetry();
         return this.appendUntil(input.getChar(), input, stop);
      }
   }

   public final char appendUntil(char ch, CharInput input, char stop1, char stop2) {
      try {
         return super.appendUntil(ch, input, stop1, stop2);
      } catch (ArrayIndexOutOfBoundsException var6) {
         this.expandAndRetry();
         return this.appendUntil(input.getChar(), input, stop1, stop2);
      }
   }

   public final char appendUntil(char ch, CharInput input, char stop1, char stop2, char stop3) {
      try {
         return super.appendUntil(ch, input, stop1, stop2, stop3);
      } catch (ArrayIndexOutOfBoundsException var7) {
         this.expandAndRetry();
         return this.appendUntil(input.getChar(), input, stop1, stop2, stop3);
      }
   }

   public final void append(char[] ch, int from, int length) {
      if (this.index + length <= this.chars.length) {
         super.append(ch, from, length);
      } else {
         this.chars = Arrays.copyOf(this.chars, Math.min(this.chars.length + length + this.index, 2147483639));
         super.append(ch, from, length);
      }

   }

   public final void append(String string, int from, int to) {
      try {
         super.append(string, from, to);
      } catch (IndexOutOfBoundsException var5) {
         this.expand(to - from);
         super.append(string, from, to);
      }

   }
}
