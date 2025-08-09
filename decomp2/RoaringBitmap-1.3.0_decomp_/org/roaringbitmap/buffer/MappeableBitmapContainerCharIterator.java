package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;

final class MappeableBitmapContainerCharIterator implements PeekableCharIterator {
   private static final int len = 1024;
   private long w;
   int x;
   private MappeableBitmapContainer parent;

   MappeableBitmapContainerCharIterator() {
   }

   MappeableBitmapContainerCharIterator(MappeableBitmapContainer p) {
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
      return this.x < 1024;
   }

   public char next() {
      char answer = (char)(this.x * 64 + Long.numberOfTrailingZeros(this.w));

      for(this.w -= 1L; this.w == 0L; this.w = this.parent.bitmap.get(this.x)) {
         ++this.x;
         if (this.x == 1024) {
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

   void wrap(MappeableBitmapContainer p) {
      this.parent = p;

      for(this.x = 0; this.x < 1024 && (this.w = this.parent.bitmap.get(this.x)) == 0L; ++this.x) {
      }

   }

   public void advanceIfNeeded(char minval) {
      if (this.hasNext()) {
         if (minval >= this.x * 64) {
            if (minval >= (this.x + 1) * 64) {
               this.x = minval / 64;
               this.w = this.parent.bitmap.get(this.x);
            }

            for(this.w &= -1L << (minval & 63); this.w == 0L; this.w = this.parent.bitmap.get(this.x)) {
               ++this.x;
               if (this.x == 1024) {
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
