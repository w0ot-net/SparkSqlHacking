package org.apache.datasketches.hll;

import org.apache.datasketches.memory.Memory;

class IntMemoryPairIterator extends PairIterator {
   private final Memory mem;
   private final long offsetBytes;
   private final int arrLen;
   private final int slotMask;
   private int index;
   private int pair;

   IntMemoryPairIterator(Memory mem, long offsetBytes, int arrayLength, int lgConfigK) {
      this.mem = mem;
      this.offsetBytes = offsetBytes;
      this.arrLen = arrayLength;
      this.slotMask = (1 << lgConfigK) - 1;
      this.index = -1;
   }

   IntMemoryPairIterator(byte[] byteArr, long offsetBytes, int lengthPairs, int lgConfigK) {
      this(Memory.wrap(byteArr), offsetBytes, lengthPairs, lgConfigK);
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
         this.pair = this.pair();
         return true;
      } else {
         return false;
      }
   }

   public boolean nextValid() {
      while(true) {
         if (++this.index < this.arrLen) {
            int pair = this.pair();
            if (pair == 0) {
               continue;
            }

            this.pair = pair;
            return true;
         }

         return false;
      }
   }

   int pair() {
      return this.mem.getInt(this.offsetBytes + (long)(this.index << 2));
   }
}
