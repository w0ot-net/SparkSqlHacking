package org.apache.datasketches.hll;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

abstract class HllArray extends AbstractHllArray {
   boolean oooFlag = false;
   boolean rebuildCurMinNumKxQ = false;
   int curMin;
   int numAtCurMin;
   double hipAccum;
   double kxq0;
   double kxq1;
   byte[] hllByteArr = null;

   HllArray(int lgConfigK, TgtHllType tgtHllType) {
      super(lgConfigK, tgtHllType, CurMode.HLL);
      this.curMin = 0;
      this.numAtCurMin = 1 << lgConfigK;
      this.hipAccum = (double)0.0F;
      this.kxq0 = (double)(1 << lgConfigK);
      this.kxq1 = (double)0.0F;
   }

   HllArray(HllArray that) {
      super(that.getLgConfigK(), that.getTgtHllType(), CurMode.HLL);
      this.oooFlag = that.isOutOfOrder();
      this.rebuildCurMinNumKxQ = that.isRebuildCurMinNumKxQFlag();
      this.curMin = that.getCurMin();
      this.numAtCurMin = that.getNumAtCurMin();
      this.hipAccum = that.getHipAccum();
      this.kxq0 = that.getKxQ0();
      this.kxq1 = that.getKxQ1();
      this.hllByteArr = (byte[])that.hllByteArr.clone();
      AuxHashMap thatAuxMap = that.getAuxHashMap();
      if (thatAuxMap != null) {
         this.putAuxHashMap(thatAuxMap.copy(), false);
      } else {
         this.putAuxHashMap((AuxHashMap)null, false);
      }

   }

   static final HllArray newHeapHll(int lgConfigK, TgtHllType tgtHllType) {
      if (tgtHllType == TgtHllType.HLL_4) {
         return new Hll4Array(lgConfigK);
      } else {
         return (HllArray)(tgtHllType == TgtHllType.HLL_6 ? new Hll6Array(lgConfigK) : new Hll8Array(lgConfigK));
      }
   }

   void addToHipAccum(double delta) {
      this.hipAccum += delta;
   }

   void decNumAtCurMin() {
      --this.numAtCurMin;
   }

   int getCurMin() {
      return this.curMin;
   }

   CurMode getCurMode() {
      return this.curMode;
   }

   double getHipAccum() {
      return this.hipAccum;
   }

   int getHllByteArrBytes() {
      return this.hllByteArr.length;
   }

   double getKxQ0() {
      return this.kxq0;
   }

   double getKxQ1() {
      return this.kxq1;
   }

   int getLgConfigK() {
      return this.lgConfigK;
   }

   Memory getMemory() {
      return null;
   }

   AuxHashMap getNewAuxHashMap() {
      return new HeapAuxHashMap(HllUtil.LG_AUX_ARR_INTS[this.lgConfigK], this.lgConfigK);
   }

   int getNumAtCurMin() {
      return this.numAtCurMin;
   }

   WritableMemory getWritableMemory() {
      return null;
   }

   boolean isCompact() {
      return false;
   }

   boolean isEmpty() {
      return false;
   }

   boolean isMemory() {
      return false;
   }

   boolean isOffHeap() {
      return false;
   }

   boolean isOutOfOrder() {
      return this.oooFlag;
   }

   boolean isSameResource(Memory mem) {
      return false;
   }

   boolean isRebuildCurMinNumKxQFlag() {
      return this.rebuildCurMinNumKxQ;
   }

   void putAuxHashMap(AuxHashMap auxHashMap, boolean compact) {
      this.auxHashMap = auxHashMap;
   }

   void putCurMin(int curMin) {
      this.curMin = curMin;
   }

   void putEmptyFlag(boolean empty) {
   }

   void putHipAccum(double value) {
      this.hipAccum = value;
   }

   void putKxQ0(double kxq0) {
      this.kxq0 = kxq0;
   }

   void putKxQ1(double kxq1) {
      this.kxq1 = kxq1;
   }

   void putNumAtCurMin(int numAtCurMin) {
      this.numAtCurMin = numAtCurMin;
   }

   void putOutOfOrder(boolean oooFlag) {
      if (oooFlag) {
         this.putHipAccum((double)0.0F);
      }

      this.oooFlag = oooFlag;
   }

   void putRebuildCurMinNumKxQFlag(boolean rebuild) {
      this.rebuildCurMinNumKxQ = rebuild;
   }

   HllSketchImpl reset() {
      return new CouponList(this.lgConfigK, this.tgtHllType, CurMode.LIST);
   }

   byte[] toCompactByteArray() {
      return this.toUpdatableByteArray();
   }

   byte[] toUpdatableByteArray() {
      return ToByteArrayImpl.toHllByteArray(this, false);
   }

   static final void extractCommonHll(Memory srcMem, HllArray hllArray) {
      hllArray.putOutOfOrder(PreambleUtil.extractOooFlag(srcMem));
      hllArray.putEmptyFlag(PreambleUtil.extractEmptyFlag(srcMem));
      hllArray.putCurMin(PreambleUtil.extractCurMin(srcMem));
      hllArray.putHipAccum(PreambleUtil.extractHipAccum(srcMem));
      hllArray.putKxQ0(PreambleUtil.extractKxQ0(srcMem));
      hllArray.putKxQ1(PreambleUtil.extractKxQ1(srcMem));
      hllArray.putNumAtCurMin(PreambleUtil.extractNumAtCurMin(srcMem));
      hllArray.putRebuildCurMinNumKxQFlag(PreambleUtil.extractRebuildCurMinNumKxQFlag(srcMem));
      srcMem.getByteArray((long)PreambleUtil.HLL_BYTE_ARR_START, hllArray.hllByteArr, 0, hllArray.hllByteArr.length);
   }
}
