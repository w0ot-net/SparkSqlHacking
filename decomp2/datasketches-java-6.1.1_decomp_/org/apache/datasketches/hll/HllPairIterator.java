package org.apache.datasketches.hll;

abstract class HllPairIterator extends PairIterator {
   final int lengthPairs;
   int index;
   int value;

   HllPairIterator(int lengthPairs) {
      this.lengthPairs = lengthPairs;
      this.index = -1;
   }

   public String getHeader() {
      return String.format("%10s%6s", "Slot", "Value");
   }

   public int getIndex() {
      return this.index;
   }

   public int getKey() {
      return this.index;
   }

   public int getPair() {
      return HllUtil.pair(this.index, this.value);
   }

   public int getSlot() {
      return this.index;
   }

   public String getString() {
      int slot = this.getSlot();
      int value = this.getValue();
      return String.format("%10d%6d", slot, value);
   }

   public int getValue() {
      return this.value;
   }

   public boolean nextAll() {
      if (++this.index < this.lengthPairs) {
         this.value = this.value();
         return true;
      } else {
         return false;
      }
   }

   public boolean nextValid() {
      while(true) {
         if (++this.index < this.lengthPairs) {
            this.value = this.value();
            if (this.value == 0) {
               continue;
            }

            return true;
         }

         return false;
      }
   }

   abstract int value();
}
