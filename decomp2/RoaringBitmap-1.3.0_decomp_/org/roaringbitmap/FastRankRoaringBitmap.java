package org.roaringbitmap;

import java.util.Arrays;

public class FastRankRoaringBitmap extends RoaringBitmap {
   private boolean cumulatedCardinalitiesCacheIsValid = false;
   private int[] highToCumulatedCardinality = null;

   public FastRankRoaringBitmap() {
   }

   public FastRankRoaringBitmap(RoaringArray array) {
      super(array);
   }

   private void resetCache() {
      this.cumulatedCardinalitiesCacheIsValid = false;
   }

   boolean isCacheDismissed() {
      return !this.cumulatedCardinalitiesCacheIsValid;
   }

   public void add(long rangeStart, long rangeEnd) {
      this.resetCache();
      super.add(rangeStart, rangeEnd);
   }

   public void add(int x) {
      this.resetCache();
      super.add(x);
   }

   public void add(int... dat) {
      this.resetCache();
      super.add(dat);
   }

   /** @deprecated */
   @Deprecated
   public void add(int rangeStart, int rangeEnd) {
      this.resetCache();
      super.add(rangeStart, rangeEnd);
   }

   public void clear() {
      this.resetCache();
      super.clear();
   }

   public void flip(int x) {
      this.resetCache();
      super.flip(x);
   }

   /** @deprecated */
   @Deprecated
   public void flip(int rangeStart, int rangeEnd) {
      this.resetCache();
      super.flip(rangeStart, rangeEnd);
   }

   public void flip(long rangeStart, long rangeEnd) {
      this.resetCache();
      super.flip(rangeStart, rangeEnd);
   }

   public void and(RoaringBitmap x2) {
      this.resetCache();
      super.and(x2);
   }

   public void andNot(RoaringBitmap x2) {
      this.resetCache();
      super.andNot(x2);
   }

   /** @deprecated */
   @Deprecated
   public void remove(int rangeStart, int rangeEnd) {
      this.resetCache();
      super.remove(rangeStart, rangeEnd);
   }

   public void remove(int x) {
      this.resetCache();
      super.remove(x);
   }

   public void remove(long rangeStart, long rangeEnd) {
      this.resetCache();
      super.remove(rangeStart, rangeEnd);
   }

   public boolean checkedAdd(int x) {
      this.resetCache();
      return super.checkedAdd(x);
   }

   public boolean checkedRemove(int x) {
      this.resetCache();
      return super.checkedRemove(x);
   }

   public void or(RoaringBitmap x2) {
      this.resetCache();
      super.or(x2);
   }

   public void xor(RoaringBitmap x2) {
      this.resetCache();
      super.xor(x2);
   }

   private void preComputeCardinalities() {
      if (!this.cumulatedCardinalitiesCacheIsValid) {
         int nbBuckets = this.highLowContainer.size();
         if (this.highToCumulatedCardinality == null || this.highToCumulatedCardinality.length != nbBuckets) {
            this.highToCumulatedCardinality = new int[nbBuckets];
         }

         if (this.highToCumulatedCardinality.length >= 1) {
            this.highToCumulatedCardinality[0] = this.highLowContainer.getContainerAtIndex(0).getCardinality();

            for(int i = 1; i < this.highToCumulatedCardinality.length; ++i) {
               this.highToCumulatedCardinality[i] = this.highToCumulatedCardinality[i - 1] + this.highLowContainer.getContainerAtIndex(i).getCardinality();
            }
         }

         this.cumulatedCardinalitiesCacheIsValid = true;
      }

   }

   public long rankLong(int x) {
      this.preComputeCardinalities();
      if (this.highLowContainer.size() == 0) {
         return 0L;
      } else {
         char xhigh = Util.highbits(x);
         int index = Util.hybridUnsignedBinarySearch(this.highLowContainer.keys, 0, this.highLowContainer.size(), xhigh);
         boolean hasBitmapOnIdex;
         if (index < 0) {
            hasBitmapOnIdex = false;
            index = -1 - index;
         } else {
            hasBitmapOnIdex = true;
         }

         long size = 0L;
         if (index > 0) {
            size += (long)this.highToCumulatedCardinality[index - 1];
         }

         long rank = size;
         if (hasBitmapOnIdex) {
            rank = size + (long)this.highLowContainer.getContainerAtIndex(index).rank(Util.lowbits(x));
         }

         return rank;
      }
   }

   public int select(int j) {
      this.preComputeCardinalities();
      if (this.highLowContainer.size() == 0) {
         throw new IllegalArgumentException("select " + j + " when the cardinality is " + this.getCardinality());
      } else {
         int maxCardinality = this.highToCumulatedCardinality[this.highToCumulatedCardinality.length - 1] - 1;
         if (j == maxCardinality) {
            return this.last();
         } else if (j > maxCardinality) {
            throw new IllegalArgumentException("select " + j + " when the cardinality is " + this.getCardinality());
         } else {
            int index = Arrays.binarySearch(this.highToCumulatedCardinality, j);
            long leftover = Util.toUnsignedLong(j);
            if (index >= 0) {
               int keycontrib = this.highLowContainer.getKeyAtIndex(index + 1) << 16;
               int output = keycontrib + this.highLowContainer.getContainerAtIndex(index + 1).first();
               return output;
            } else {
               int fixedIndex = -1 - index;
               if (fixedIndex > 0) {
                  leftover -= (long)this.highToCumulatedCardinality[fixedIndex - 1];
               }

               int keycontrib = this.highLowContainer.getKeyAtIndex(fixedIndex) << 16;
               int lowcontrib = this.highLowContainer.getContainerAtIndex(fixedIndex).select((int)leftover);
               int value = lowcontrib + keycontrib;
               return value;
            }
         }
      }
   }

   public long getLongSizeInBytes() {
      long size = 8L;
      size += super.getLongSizeInBytes();
      if (this.highToCumulatedCardinality != null) {
         size += 4L * (long)this.highToCumulatedCardinality.length;
      }

      return size;
   }

   public PeekableIntRankIterator getIntRankIterator() {
      this.preComputeCardinalities();
      return new FastRoaringIntRankIterator();
   }

   private class FastRoaringIntRankIterator implements PeekableIntRankIterator {
      private int hs;
      private PeekableCharRankIterator iter;
      private int pos;

      private FastRoaringIntRankIterator() {
         this.hs = 0;
         this.pos = 0;
         this.nextContainer();
      }

      public int peekNextRank() {
         int iterRank = this.iter.peekNextRank();
         return this.pos > 0 ? FastRankRoaringBitmap.this.highToCumulatedCardinality[this.pos - 1] + iterRank : iterRank;
      }

      public PeekableIntRankIterator clone() {
         try {
            FastRoaringIntRankIterator x = (FastRoaringIntRankIterator)super.clone();
            if (this.iter != null) {
               x.iter = this.iter.clone();
            }

            return x;
         } catch (CloneNotSupportedException var2) {
            return null;
         }
      }

      public boolean hasNext() {
         return this.pos < FastRankRoaringBitmap.this.highLowContainer.size();
      }

      public int next() {
         int x = this.iter.nextAsInt() | this.hs;
         if (!this.iter.hasNext()) {
            ++this.pos;
            this.nextContainer();
         }

         return x;
      }

      private void nextContainer() {
         if (this.pos < FastRankRoaringBitmap.this.highLowContainer.size()) {
            this.iter = FastRankRoaringBitmap.this.highLowContainer.getContainerAtIndex(this.pos).getCharRankIterator();
            this.hs = FastRankRoaringBitmap.this.highLowContainer.getKeyAtIndex(this.pos) << 16;
         }

      }

      public void advanceIfNeeded(int minval) {
         while(this.hasNext() && this.hs >>> 16 < minval >>> 16) {
            ++this.pos;
            this.nextContainer();
         }

         if (this.hasNext() && this.hs >>> 16 == minval >>> 16) {
            this.iter.advanceIfNeeded(Util.lowbits(minval));
            if (!this.iter.hasNext()) {
               ++this.pos;
               this.nextContainer();
            }
         }

      }

      public int peekNext() {
         return this.iter.peekNext() | this.hs;
      }
   }
}
