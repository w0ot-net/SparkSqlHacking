package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

final class HeapAuxHashMap implements AuxHashMap {
   private final int lgConfigK;
   private int lgAuxArrInts;
   private int auxCount;
   private int[] auxIntArr;

   HeapAuxHashMap(int lgAuxArrInts, int lgConfigK) {
      this.lgConfigK = lgConfigK;
      this.lgAuxArrInts = lgAuxArrInts;
      this.auxIntArr = new int[1 << lgAuxArrInts];
   }

   HeapAuxHashMap(HeapAuxHashMap that) {
      this.lgConfigK = that.lgConfigK;
      this.lgAuxArrInts = that.lgAuxArrInts;
      this.auxCount = that.auxCount;
      this.auxIntArr = (int[])that.auxIntArr.clone();
   }

   static final HeapAuxHashMap heapify(Memory mem, long offset, int lgConfigK, int auxCount, boolean srcCompact) {
      int lgAuxArrInts;
      if (srcCompact) {
         lgAuxArrInts = PreambleUtil.computeLgArr(mem, auxCount, lgConfigK);
      } else {
         lgAuxArrInts = PreambleUtil.extractLgArr(mem);
      }

      HeapAuxHashMap auxMap = new HeapAuxHashMap(lgAuxArrInts, lgConfigK);
      int configKmask = (1 << lgConfigK) - 1;
      if (srcCompact) {
         for(int i = 0; i < auxCount; ++i) {
            int pair = PreambleUtil.extractInt(mem, offset + (long)(i << 2));
            int slotNo = HllUtil.getPairLow26(pair) & configKmask;
            int value = HllUtil.getPairValue(pair);
            auxMap.mustAdd(slotNo, value);
         }
      } else {
         int auxArrInts = 1 << lgAuxArrInts;

         for(int i = 0; i < auxArrInts; ++i) {
            int pair = PreambleUtil.extractInt(mem, offset + (long)(i << 2));
            if (pair != 0) {
               int slotNo = HllUtil.getPairLow26(pair) & configKmask;
               int value = HllUtil.getPairValue(pair);
               auxMap.mustAdd(slotNo, value);
            }
         }
      }

      return auxMap;
   }

   public HeapAuxHashMap copy() {
      return new HeapAuxHashMap(this);
   }

   public int getAuxCount() {
      return this.auxCount;
   }

   public int[] getAuxIntArr() {
      return this.auxIntArr;
   }

   public int getCompactSizeBytes() {
      return this.auxCount << 2;
   }

   public PairIterator getIterator() {
      return new IntArrayPairIterator(this.auxIntArr, this.lgConfigK);
   }

   public int getLgAuxArrInts() {
      return this.lgAuxArrInts;
   }

   public int getUpdatableSizeBytes() {
      return 4 << this.lgAuxArrInts;
   }

   public boolean isMemory() {
      return false;
   }

   public boolean isOffHeap() {
      return false;
   }

   public void mustAdd(int slotNo, int value) {
      int index = find(this.auxIntArr, this.lgAuxArrInts, this.lgConfigK, slotNo);
      int pair = HllUtil.pair(slotNo, value);
      if (index >= 0) {
         String pairStr = HllUtil.pairString(pair);
         throw new SketchesStateException("Found a slotNo that should not be there: " + pairStr);
      } else {
         this.auxIntArr[~index] = pair;
         ++this.auxCount;
         this.checkGrow();
      }
   }

   public int mustFindValueFor(int slotNo) {
      int index = find(this.auxIntArr, this.lgAuxArrInts, this.lgConfigK, slotNo);
      if (index >= 0) {
         return HllUtil.getPairValue(this.auxIntArr[index]);
      } else {
         throw new SketchesStateException("SlotNo not found: " + slotNo);
      }
   }

   public void mustReplace(int slotNo, int value) {
      int idx = find(this.auxIntArr, this.lgAuxArrInts, this.lgConfigK, slotNo);
      if (idx >= 0) {
         this.auxIntArr[idx] = HllUtil.pair(slotNo, value);
      } else {
         String pairStr = HllUtil.pairString(HllUtil.pair(slotNo, value));
         throw new SketchesStateException("Pair not found: " + pairStr);
      }
   }

   private static final int find(int[] auxArr, int lgAuxArrInts, int lgConfigK, int slotNo) {
      assert lgAuxArrInts < lgConfigK;

      int auxArrMask = (1 << lgAuxArrInts) - 1;
      int configKmask = (1 << lgConfigK) - 1;
      int probe = slotNo & auxArrMask;
      int loopIndex = probe;

      do {
         int arrVal = auxArr[probe];
         if (arrVal == 0) {
            return ~probe;
         }

         if (slotNo == (arrVal & configKmask)) {
            return probe;
         }

         int stride = slotNo >>> lgAuxArrInts | 1;
         probe = probe + stride & auxArrMask;
      } while(probe != loopIndex);

      throw new SketchesArgumentException("Key not found and no empty slots!");
   }

   private void checkGrow() {
      if (4 * this.auxCount > 3 * this.auxIntArr.length) {
         this.growAuxSpace();
      }

   }

   private void growAuxSpace() {
      int[] oldArray = this.auxIntArr;
      int configKmask = (1 << this.lgConfigK) - 1;
      this.auxIntArr = new int[1 << ++this.lgAuxArrInts];

      for(int i = 0; i < oldArray.length; ++i) {
         int fetched = oldArray[i];
         if (fetched != 0) {
            int idx = find(this.auxIntArr, this.lgAuxArrInts, this.lgConfigK, fetched & configKmask);
            this.auxIntArr[~idx] = fetched;
         }
      }

   }
}
