package com.ibm.icu.message2;

class StringView implements CharSequence {
   final int offset;
   final String text;

   StringView(String text, int offset) {
      this.offset = offset;
      this.text = text;
   }

   StringView(String text) {
      this(text, 0);
   }

   public int length() {
      return this.text.length() - this.offset;
   }

   public char charAt(int index) {
      return this.text.charAt(index + this.offset);
   }

   public CharSequence subSequence(int start, int end) {
      return this.text.subSequence(start + this.offset, end + this.offset);
   }

   public String toString() {
      return this.text.substring(this.offset);
   }
}
