package org.apache.datasketches.hll;

class IntArrayPairIterator extends PairIterator {
   private final int[] array;
   private final int arrLen;
   private final int slotMask;
   private int index;
   private int pair;

   IntArrayPairIterator(int[] array, int lgConfigK) {
      this.array = array;
      this.slotMask = (1 << lgConfigK) - 1;
      this.arrLen = array.length;
      this.index = -1;
   }

   public int getIndex() {
      return this.index;
   }

   public int getKey() {
      return HllUtil.getPairLow26(this.pair);
   }

   public int getPair() {
      return this.pair;
   }

   public int getSlot() {
      return this.getKey() & this.slotMask;
   }

   public int getValue() {
      return HllUtil.getPairValue(this.pair);
   }

   public boolean nextAll() {
      if (++this.index < this.arrLen) {
         this.pair = this.array[this.index];
         return true;
      } else {
         return false;
      }
   }

   public boolean nextValid() {
      while(true) {
         if (++this.index < this.arrLen) {
            int pair = this.array[this.index];
            if (pair == 0) {
               continue;
            }

            this.pair = pair;
            return true;
         }

         return false;
      }
   }
}
