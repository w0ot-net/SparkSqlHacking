package org.roaringbitmap;

final class ReverseBitmapContainerCharIterator implements PeekableCharIterator {
   long word;
   int position;
   long[] bitmap;

   ReverseBitmapContainerCharIterator() {
   }

   ReverseBitmapContainerCharIterator(long[] bitmap) {
      this.wrap(bitmap);
   }

   public PeekableCharIterator clone() {
      try {
         return (PeekableCharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.position >= 0;
   }

   public char next() {
      int shift = Long.numberOfLeadingZeros(this.word) + 1;
      char answer = (char)((this.position + 1) * 64 - shift);

      for(this.word &= -2L >>> shift; this.word == 0L; this.word = this.bitmap[this.position]) {
         --this.position;
         if (this.position < 0) {
            break;
         }
      }

      return answer;
   }

   public int nextAsInt() {
      return this.next();
   }

   public void advanceIfNeeded(char maxval) {
      if (maxval < (this.position + 1) * 64) {
         if (maxval < this.position * 64) {
            this.position = maxval / 64;
         }

         long currentWord = this.bitmap[this.position];
         currentWord &= -1L >>> 63 - (maxval & 63);
         if (this.position > 0) {
            while(currentWord == 0L) {
               --this.position;
               if (this.position == 0) {
                  break;
               }

               currentWord = this.bitmap[this.position];
            }
         }

         this.word = currentWord;
      }

   }

   public char peekNext() {
      int shift = Long.numberOfLeadingZeros(this.word) + 1;
      return (char)((this.position + 1) * 64 - shift);
   }

   public void remove() {
      throw new RuntimeException("unsupported operation: remove");
   }

   void wrap(long[] b) {
      this.bitmap = b;

      for(this.position = this.bitmap.length - 1; this.position >= 0 && (this.word = this.bitmap[this.position]) == 0L; --this.position) {
      }

   }
}
