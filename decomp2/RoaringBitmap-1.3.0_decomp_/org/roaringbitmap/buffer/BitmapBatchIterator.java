package org.roaringbitmap.buffer;

import org.roaringbitmap.ContainerBatchIterator;

public final class BitmapBatchIterator implements ContainerBatchIterator {
   private int wordIndex = 0;
   private long word;
   private MappeableBitmapContainer bitmap;

   public BitmapBatchIterator(MappeableBitmapContainer bitmap) {
      this.wrap(bitmap);
   }

   public int next(int key, int[] buffer, int offset) {
      int consumed;
      for(consumed = 0; offset + consumed < buffer.length; this.word &= this.word - 1L) {
         while(this.word == 0L) {
            ++this.wordIndex;
            if (this.wordIndex == 1024) {
               return consumed;
            }

            this.word = this.bitmap.bitmap.get(this.wordIndex);
         }

         buffer[offset + consumed++] = key + 64 * this.wordIndex + Long.numberOfTrailingZeros(this.word);
      }

      return consumed;
   }

   public boolean hasNext() {
      if (this.wordIndex > 1023) {
         return false;
      } else {
         while(this.word == 0L) {
            ++this.wordIndex;
            if (this.wordIndex == 1024) {
               return false;
            }

            this.word = this.bitmap.bitmap.get(this.wordIndex);
         }

         return true;
      }
   }

   public ContainerBatchIterator clone() {
      try {
         return (ContainerBatchIterator)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public void releaseContainer() {
      this.bitmap = null;
   }

   public void advanceIfNeeded(char target) {
      this.wordIndex = target >>> 6;
      this.word = this.bitmap.bitmap.get(this.wordIndex);
      this.word &= -(1L << target);
   }

   void wrap(MappeableBitmapContainer bitmap) {
      this.bitmap = bitmap;
      this.word = bitmap.bitmap.get(0);
      this.wordIndex = 0;
   }
}
