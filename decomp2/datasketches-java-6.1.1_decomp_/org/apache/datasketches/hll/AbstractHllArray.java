package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;

abstract class AbstractHllArray extends HllSketchImpl {
   AuxHashMap auxHashMap = null;
   final int auxStart;

   AbstractHllArray(int lgConfigK, TgtHllType tgtHllType, CurMode curMode) {
      super(lgConfigK, tgtHllType, curMode);
      this.auxStart = PreambleUtil.HLL_BYTE_ARR_START + hll4ArrBytes(lgConfigK);
   }

   abstract void addToHipAccum(double var1);

   HllArray copyAs(TgtHllType tgtHllType) {
      if (tgtHllType == this.getTgtHllType()) {
         return (HllArray)this.copy();
      } else if (tgtHllType == TgtHllType.HLL_4) {
         return Conversions.convertToHll4(this);
      } else {
         return (HllArray)(tgtHllType == TgtHllType.HLL_6 ? Conversions.convertToHll6(this) : Conversions.convertToHll8(this));
      }
   }

   abstract void decNumAtCurMin();

   AuxHashMap getAuxHashMap() {
      return this.auxHashMap;
   }

   PairIterator getAuxIterator() {
      return this.auxHashMap == null ? null : this.auxHashMap.getIterator();
   }

   int getCompactSerializationBytes() {
      AuxHashMap auxHashMap = this.getAuxHashMap();
      int auxCountBytes = auxHashMap == null ? 0 : auxHashMap.getAuxCount() << 2;
      return PreambleUtil.HLL_BYTE_ARR_START + this.getHllByteArrBytes() + auxCountBytes;
   }

   double getCompositeEstimate() {
      return HllEstimators.hllCompositeEstimate(this);
   }

   abstract int getCurMin();

   double getEstimate() {
      return this.isOutOfOrder() ? this.getCompositeEstimate() : this.getHipAccum();
   }

   abstract double getHipAccum();

   double getHipEstimate() {
      return this.getHipAccum();
   }

   abstract int getHllByteArrBytes();

   abstract double getKxQ0();

   abstract double getKxQ1();

   double getLowerBound(int numStdDev) {
      HllUtil.checkNumStdDev(numStdDev);
      return HllEstimators.hllLowerBound(this, numStdDev);
   }

   int getMemDataStart() {
      return PreambleUtil.HLL_BYTE_ARR_START;
   }

   abstract AuxHashMap getNewAuxHashMap();

   abstract int getNumAtCurMin();

   int getPreInts() {
      return 10;
   }

   abstract int getNibble(int var1);

   abstract int getSlotValue(int var1);

   int getUpdatableSerializationBytes() {
      return PreambleUtil.HLL_BYTE_ARR_START + this.getHllByteArrBytes();
   }

   double getUpperBound(int numStdDev) {
      HllUtil.checkNumStdDev(numStdDev);
      return HllEstimators.hllUpperBound(this, numStdDev);
   }

   abstract PairIterator iterator();

   void mergeTo(HllSketch that) {
      throw new SketchesStateException("Possible Corruption, improper access.");
   }

   abstract void putAuxHashMap(AuxHashMap var1, boolean var2);

   abstract void putCurMin(int var1);

   abstract void putHipAccum(double var1);

   abstract void putKxQ0(double var1);

   abstract void putKxQ1(double var1);

   abstract void putNibble(int var1, int var2);

   abstract void putNumAtCurMin(int var1);

   abstract void updateSlotWithKxQ(int var1, int var2);

   abstract void updateSlotNoKxQ(int var1, int var2);

   static final int hll4ArrBytes(int lgConfigK) {
      return 1 << lgConfigK - 1;
   }

   static final int hll6ArrBytes(int lgConfigK) {
      int numSlots = 1 << lgConfigK;
      return (numSlots * 3 >>> 2) + 1;
   }

   static final int hll8ArrBytes(int lgConfigK) {
      return 1 << lgConfigK;
   }

   static final void hipAndKxQIncrementalUpdate(AbstractHllArray host, int oldValue, int newValue) {
      assert newValue > oldValue;

      double kxq0 = host.getKxQ0();
      double kxq1 = host.getKxQ1();
      host.addToHipAccum((double)(1 << host.getLgConfigK()) / (kxq0 + kxq1));
      incrementalUpdateKxQ(host, oldValue, newValue, kxq0, kxq1);
   }

   static final void incrementalUpdateKxQ(AbstractHllArray host, int oldValue, int newValue, double kxq0, double kxq1) {
      if (oldValue < 32) {
         host.putKxQ0(kxq0 -= Util.invPow2(oldValue));
      } else {
         host.putKxQ1(kxq1 -= Util.invPow2(oldValue));
      }

      if (newValue < 32) {
         host.putKxQ0(kxq0 + Util.invPow2(newValue));
      } else {
         host.putKxQ1(kxq1 + Util.invPow2(newValue));
      }

   }
}
