package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

abstract class DirectHllArray extends AbstractHllArray {
   WritableMemory wmem;
   Memory mem;
   Object memObj;
   long memAdd;
   final boolean compact;

   private static int checkMemCompactFlag(WritableMemory wmem, int lgConfigK) {
      assert !PreambleUtil.extractCompactFlag(wmem);

      return lgConfigK;
   }

   DirectHllArray(int lgConfigK, TgtHllType tgtHllType, WritableMemory wmem) {
      super(checkMemCompactFlag(wmem, lgConfigK), tgtHllType, CurMode.HLL);
      this.wmem = wmem;
      this.mem = wmem;
      this.memObj = wmem.getArray();
      this.memAdd = wmem.getCumulativeOffset(0L);
      this.compact = PreambleUtil.extractCompactFlag(this.mem);
      PreambleUtil.insertEmptyFlag(wmem, false);
   }

   DirectHllArray(int lgConfigK, TgtHllType tgtHllType, Memory mem) {
      super(lgConfigK, tgtHllType, CurMode.HLL);
      this.wmem = null;
      this.mem = mem;
      this.memObj = ((WritableMemory)mem).getArray();
      this.memAdd = mem.getCumulativeOffset(0L);
      this.compact = PreambleUtil.extractCompactFlag(mem);
   }

   final void updateMemory(WritableMemory newWmem) {
      this.wmem = newWmem;
      this.mem = newWmem;
      this.memObj = this.wmem.getArray();
      this.memAdd = this.wmem.getCumulativeOffset(0L);
   }

   void addToHipAccum(double delta) {
      checkReadOnly(this.wmem);
      double hipAccum = this.mem.getDouble((long)PreambleUtil.HIP_ACCUM_DOUBLE);
      this.wmem.putDouble((long)PreambleUtil.HIP_ACCUM_DOUBLE, hipAccum + delta);
   }

   void decNumAtCurMin() {
      checkReadOnly(this.wmem);
      int numAtCurMin = this.mem.getInt((long)PreambleUtil.CUR_MIN_COUNT_INT);
      long var10001 = (long)PreambleUtil.CUR_MIN_COUNT_INT;
      --numAtCurMin;
      this.wmem.putInt(var10001, numAtCurMin);
   }

   int getCurMin() {
      return PreambleUtil.extractCurMin(this.mem);
   }

   CurMode getCurMode() {
      return PreambleUtil.extractCurMode(this.mem);
   }

   double getHipAccum() {
      return PreambleUtil.extractHipAccum(this.mem);
   }

   double getKxQ0() {
      return PreambleUtil.extractKxQ0(this.mem);
   }

   double getKxQ1() {
      return PreambleUtil.extractKxQ1(this.mem);
   }

   int getLgConfigK() {
      return PreambleUtil.extractLgK(this.mem);
   }

   Memory getMemory() {
      return this.mem;
   }

   AuxHashMap getNewAuxHashMap() {
      return new DirectAuxHashMap(this, true);
   }

   int getNumAtCurMin() {
      return PreambleUtil.extractNumAtCurMin(this.mem);
   }

   TgtHllType getTgtHllType() {
      return PreambleUtil.extractTgtHllType(this.mem);
   }

   WritableMemory getWritableMemory() {
      return this.wmem;
   }

   boolean isCompact() {
      return this.compact;
   }

   boolean isEmpty() {
      return PreambleUtil.extractEmptyFlag(this.mem);
   }

   boolean isMemory() {
      return true;
   }

   boolean isOffHeap() {
      return this.mem.isDirect();
   }

   boolean isOutOfOrder() {
      return PreambleUtil.extractOooFlag(this.mem);
   }

   boolean isSameResource(Memory mem) {
      return this.mem.isSameResource(mem);
   }

   boolean isRebuildCurMinNumKxQFlag() {
      return PreambleUtil.extractRebuildCurMinNumKxQFlag(this.mem);
   }

   void putAuxHashMap(AuxHashMap auxHashMap, boolean compact) {
      if (auxHashMap instanceof HeapAuxHashMap) {
         if (compact) {
            this.auxHashMap = auxHashMap;
         } else {
            int[] auxArr = auxHashMap.getAuxIntArr();
            this.wmem.putIntArray((long)this.auxStart, auxArr, 0, auxArr.length);
            PreambleUtil.insertLgArr(this.wmem, auxHashMap.getLgAuxArrInts());
            PreambleUtil.insertAuxCount(this.wmem, auxHashMap.getAuxCount());
            this.auxHashMap = new DirectAuxHashMap(this, false);
         }
      } else {
         assert !compact;

         this.auxHashMap = auxHashMap;
      }

   }

   void putCurMin(int curMin) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertCurMin(this.wmem, curMin);
   }

   void putEmptyFlag(boolean empty) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertEmptyFlag(this.wmem, empty);
   }

   void putHipAccum(double hipAccum) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertHipAccum(this.wmem, hipAccum);
   }

   void putKxQ0(double kxq0) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertKxQ0(this.wmem, kxq0);
   }

   void putKxQ1(double kxq1) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertKxQ1(this.wmem, kxq1);
   }

   void putNumAtCurMin(int numAtCurMin) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertNumAtCurMin(this.wmem, numAtCurMin);
   }

   void putOutOfOrder(boolean oooFlag) {
      if (oooFlag) {
         this.putHipAccum((double)0.0F);
      }

      checkReadOnly(this.wmem);
      PreambleUtil.insertOooFlag(this.wmem, oooFlag);
   }

   void putRebuildCurMinNumKxQFlag(boolean rebuild) {
      checkReadOnly(this.wmem);
      PreambleUtil.insertRebuildCurMinNumKxQFlag(this.wmem, rebuild);
   }

   byte[] toCompactByteArray() {
      return this.toUpdatableByteArray();
   }

   byte[] toUpdatableByteArray() {
      int totBytes = this.getCompactSerializationBytes();
      byte[] byteArr = new byte[totBytes];
      WritableMemory memOut = WritableMemory.writableWrap(byteArr);
      this.mem.copyTo(0L, memOut, 0L, (long)totBytes);
      PreambleUtil.insertCompactFlag(memOut, false);
      return byteArr;
   }

   HllSketchImpl reset() {
      checkReadOnly(this.wmem);
      PreambleUtil.insertEmptyFlag(this.wmem, true);
      int bytes = HllSketch.getMaxUpdatableSerializationBytes(this.lgConfigK, this.tgtHllType);
      this.wmem.clear(0L, (long)bytes);
      return DirectCouponList.newInstance(this.lgConfigK, this.tgtHllType, this.wmem);
   }

   private static final void checkReadOnly(WritableMemory wmem) {
      if (wmem == null) {
         throw new SketchesArgumentException("Cannot modify a read-only sketch");
      }
   }
}
