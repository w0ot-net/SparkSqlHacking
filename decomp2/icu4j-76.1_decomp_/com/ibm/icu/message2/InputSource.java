package com.ibm.icu.message2;

class InputSource {
   final String buffer;
   private int cursor;
   private int lastReadCursor = -1;
   private int lastReadCount = 0;

   InputSource(String input) {
      if (input == null) {
         throw new IllegalArgumentException("Input string should not be null");
      } else {
         this.buffer = input;
         this.cursor = 0;
      }
   }

   boolean atEnd() {
      return this.cursor >= this.buffer.length();
   }

   int peekChar() {
      return this.atEnd() ? -1 : this.buffer.charAt(this.cursor);
   }

   int readCodePoint() {
      if (this.lastReadCursor != this.cursor) {
         this.lastReadCursor = this.cursor;
         this.lastReadCount = 1;
      } else {
         ++this.lastReadCount;
         if (this.lastReadCount >= 10) {
            throw new RuntimeException("Stuck in a loop!");
         }
      }

      if (this.atEnd()) {
         return -1;
      } else {
         char c = this.buffer.charAt(this.cursor++);
         if (Character.isHighSurrogate(c) && !this.atEnd()) {
            char c2 = this.buffer.charAt(this.cursor++);
            if (Character.isLowSurrogate(c2)) {
               return Character.toCodePoint(c, c2);
            } else {
               --this.cursor;
               return c;
            }
         } else {
            return c;
         }
      }
   }

   void backup(int amount) {
      this.cursor -= amount;
   }

   int getPosition() {
      return this.cursor;
   }

   void skip(int amount) {
      this.cursor += amount;
   }

   void gotoPosition(int position) {
      this.cursor = position;
   }
}
