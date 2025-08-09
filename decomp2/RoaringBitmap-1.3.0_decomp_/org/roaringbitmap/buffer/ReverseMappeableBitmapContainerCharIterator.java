package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;

final class ReverseMappeableBitmapContainerCharIterator implements CharIterator {
   private static final int len = 1024;
   private long w;
   int x;
   private MappeableBitmapContainer parent;

   ReverseMappeableBitmapContainerCharIterator() {
   }

   ReverseMappeableBitmapContainerCharIterator(MappeableBitmapContainer p) {
      this.wrap(p);
   }

   public CharIterator clone() {
      try {
         return (CharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.x >= 0;
   }

   public char next() {
      int shift = Long.numberOfLeadingZeros(this.w) + 1;
      char answer = (char)((this.x + 1) * 64 - shift);

      for(this.w &= -2L >>> shift; this.w == 0L; this.w = this.parent.bitmap.get(this.x)) {
         --this.x;
         if (this.x < 0) {
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

   public void wrap(MappeableBitmapContainer p) {
      this.parent = p;

      for(this.x = 1023; this.x >= 0 && (this.w = this.parent.bitmap.get(this.x)) == 0L; --this.x) {
      }

   }
}
