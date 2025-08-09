package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class DirectAuxHashMap implements AuxHashMap {
   private final DirectHllArray host;
   private final boolean readOnly;

   DirectAuxHashMap(DirectHllArray host, boolean initialize) {
      this.host = host;
      this.readOnly = host.wmem == null;
      int initLgArrInts = HllUtil.LG_AUX_ARR_INTS[host.lgConfigK];
      if (initialize) {
         if (this.readOnly) {
            HllUtil.noWriteAccess();
         }

         PreambleUtil.insertLgArr(host.wmem, initLgArrInts);
         host.wmem.clear((long)host.auxStart, (long)(4 << initLgArrInts));
      } else if (PreambleUtil.extractLgArr(host.mem) < initLgArrInts) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Possible Memory image corruption, incorrect LgArr field in preamble.");
         }

         int lgArr = PreambleUtil.computeLgArr(host.wmem, host.auxHashMap.getAuxCount(), host.lgConfigK);
         PreambleUtil.insertLgArr(host.wmem, lgArr);
      }

   }

   public DirectAuxHashMap copy() {
      return null;
   }

   public int getAuxCount() {
      return PreambleUtil.extractAuxCount(this.host.mem);
   }

   public int[] getAuxIntArr() {
      return null;
   }

   public int getCompactSizeBytes() {
      return this.getAuxCount() << 2;
   }

   public PairIterator getIterator() {
      return new IntMemoryPairIterator(this.host.mem, (long)this.host.auxStart, 1 << this.getLgAuxArrInts(), this.host.lgConfigK);
   }

   public int getLgAuxArrInts() {
      return PreambleUtil.extractLgArr(this.host.mem);
   }

   public int getUpdatableSizeBytes() {
      return 4 << this.getLgAuxArrInts();
   }

   public boolean isMemory() {
      return true;
   }

   public boolean isOffHeap() {
      return this.host.isOffHeap();
   }

   public void mustAdd(int slotNo, int value) {
      if (this.readOnly) {
         HllUtil.noWriteAccess();
      }

      int index = find(this.host, slotNo);
      int pair = HllUtil.pair(slotNo, value);
      if (index >= 0) {
         String pairStr = HllUtil.pairString(pair);
         throw new SketchesStateException("Found a slotNo that should not be there: " + pairStr);
      } else {
         this.host.wmem.putInt((long)(this.host.auxStart + (~index << 2)), pair);
         int auxCount = PreambleUtil.extractAuxCount(this.host.mem);
         ++auxCount;
         PreambleUtil.insertAuxCount(this.host.wmem, auxCount);
         int lgAuxArrInts = PreambleUtil.extractLgArr(this.host.mem);
         if (4 * auxCount > 3 * (1 << lgAuxArrInts)) {
            grow(this.host, lgAuxArrInts);
         }

      }
   }

   public int mustFindValueFor(int slotNo) {
      int index = find(this.host, slotNo);
      if (index >= 0) {
         int pair = this.host.mem.getInt((long)(this.host.auxStart + (index << 2)));
         return HllUtil.getPairValue(pair);
      } else {
         throw new SketchesStateException("SlotNo not found: " + slotNo);
      }
   }

   public void mustReplace(int slotNo, int value) {
      if (this.readOnly) {
         HllUtil.noWriteAccess();
      }

      int index = find(this.host, slotNo);
      if (index >= 0) {
         this.host.wmem.putInt((long)(this.host.auxStart + (index << 2)), HllUtil.pair(slotNo, value));
      } else {
         String pairStr = HllUtil.pairString(HllUtil.pair(slotNo, value));
         throw new SketchesStateException("Pair not found: " + pairStr);
      }
   }

   private static final int find(DirectHllArray host, int slotNo) {
      int lgAuxArrInts = PreambleUtil.extractLgArr(host.mem);

      assert lgAuxArrInts < host.lgConfigK : lgAuxArrInts;

      int auxInts = 1 << lgAuxArrInts;
      int auxArrMask = auxInts - 1;
      int configKmask = (1 << host.lgConfigK) - 1;
      int probe = slotNo & auxArrMask;
      int loopIndex = probe;

      do {
         int arrVal = host.mem.getInt((long)(host.auxStart + (probe << 2)));
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

   private static final void grow(DirectHllArray host, int oldLgAuxArrInts) {
      if (host.wmem == null) {
         HllUtil.noWriteAccess();
      }

      int oldAuxArrInts = 1 << oldLgAuxArrInts;
      int[] oldIntArray = new int[oldAuxArrInts];
      host.wmem.getIntArray((long)host.auxStart, oldIntArray, 0, oldAuxArrInts);
      PreambleUtil.insertLgArr(host.wmem, oldLgAuxArrInts + 1);
      long newAuxBytes = (long)(oldAuxArrInts << 3);
      long requestBytes = (long)host.auxStart + newAuxBytes;
      long oldCapBytes = host.wmem.getCapacity();
      if (requestBytes > oldCapBytes) {
         MemoryRequestServer svr = host.wmem.getMemoryRequestServer();
         WritableMemory newWmem = svr.request(host.wmem, requestBytes);
         host.wmem.copyTo(0L, newWmem, 0L, (long)host.auxStart);
         newWmem.clear((long)host.auxStart, newAuxBytes);
         svr.requestClose(host.wmem, newWmem);
         host.updateMemory(newWmem);
      }

      int configKmask = (1 << host.lgConfigK) - 1;

      for(int i = 0; i < oldAuxArrInts; ++i) {
         int fetched = oldIntArray[i];
         if (fetched != 0) {
            int index = find(host, fetched & configKmask);
            host.wmem.putInt((long)(host.auxStart + (~index << 2)), fetched);
         }
      }

   }
}
