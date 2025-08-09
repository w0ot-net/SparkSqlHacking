package com.univocity.parsers.common.input;

public class ElasticCharAppender extends ExpandingCharAppender {
   private static final char[] EMPTY_CHAR_ARRAY = new char[0];
   private int defaultLength;

   public ElasticCharAppender(String emptyValue) {
      this(4096, emptyValue);
   }

   public ElasticCharAppender(int defaultLength, String emptyValue) {
      super(defaultLength, emptyValue, 0);
      this.defaultLength = defaultLength;
   }

   public String getAndReset() {
      String out = super.getAndReset();
      if (this.chars.length > this.defaultLength) {
         this.chars = new char[this.defaultLength];
      }

      return out;
   }

   public char[] getCharsAndReset() {
      char[] out = super.getCharsAndReset();
      if (this.chars.length > this.defaultLength) {
         this.chars = new char[this.defaultLength];
      }

      return out;
   }

   public void reset() {
      if (this.chars.length > this.defaultLength) {
         this.chars = new char[this.defaultLength];
      }

      super.reset();
   }

   public String getTrimmedStringAndReset() {
      int length = this.index - this.whitespaceCount;

      int start;
      for(start = 0; start < length && this.chars[start] <= ' '; ++start) {
      }

      if (start >= length) {
         return this.emptyValue;
      } else {
         while(this.chars[length - 1] <= ' ') {
            --length;
         }

         length -= start;
         if (length <= 0) {
            return this.emptyValue;
         } else {
            String out = new String(this.chars, start, length);
            this.reset();
            return out;
         }
      }
   }

   public char[] getTrimmedCharsAndReset() {
      int length = this.index - this.whitespaceCount;

      int start;
      for(start = 0; start < length && this.chars[start] <= ' '; ++start) {
      }

      if (start >= length) {
         return EMPTY_CHAR_ARRAY;
      } else {
         while(this.chars[length - 1] <= ' ') {
            --length;
         }

         length -= start;
         if (length <= 0) {
            return EMPTY_CHAR_ARRAY;
         } else {
            char[] out = new char[length];
            System.arraycopy(this.chars, start, out, 0, length);
            this.reset();
            return out;
         }
      }
   }
}
