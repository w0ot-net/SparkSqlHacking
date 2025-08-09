package org.roaringbitmap;

class BitmapContainerCharIterator implements PeekableCharIterator {
   long w;
   int x;
   long[] bitmap;

   BitmapContainerCharIterator() {
   }

   BitmapContainerCharIterator(long[] p) {
      this.wrap(p);
   }

   public PeekableCharIterator clone() {
      try {
         return (PeekableCharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.x < this.bitmap.length;
   }

   public char next() {
      char answer = (char)(this.x * 64 + Long.numberOfTrailingZeros(this.w));

      for(this.w -= 1L; this.w == 0L; this.w = this.bitmap[this.x]) {
         ++this.x;
         if (this.x == this.bitmap.length) {
            break;
         }
      }

      return answer;
   }

   public int nextAsInt() {
      return this.next();
   }

   public void remove() {
      throw new RuntimeException("unsupported operation: remove");
   }

   public void wrap(long[] b) {
      this.bitmap = b;

      for(this.x = 0; this.x < this.bitmap.length && (this.w = this.bitmap[this.x]) == 0L; ++this.x) {
      }

   }

   public void advanceIfNeeded(char minval) {
      if (this.hasNext()) {
         if (minval >= this.x * 64) {
            if (minval >= (this.x + 1) * 64) {
               this.x = minval / 64;
               this.w = this.bitmap[this.x];
            }

            for(this.w &= -1L << (minval & 63); this.w == 0L; this.w = this.bitmap[this.x]) {
               ++this.x;
               if (!this.hasNext()) {
                  return;
               }
            }
         }

      }
   }

   public char peekNext() {
      return (char)(this.x * 64 + Long.numberOfTrailingZeros(this.w));
   }
}
