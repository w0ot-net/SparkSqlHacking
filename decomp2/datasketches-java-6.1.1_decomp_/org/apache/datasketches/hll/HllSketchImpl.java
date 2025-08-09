package org.apache.datasketches.hll;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

abstract class HllSketchImpl {
   final int lgConfigK;
   final TgtHllType tgtHllType;
   final CurMode curMode;

   HllSketchImpl(int lgConfigK, TgtHllType tgtHllType, CurMode curMode) {
      this.lgConfigK = lgConfigK;
      this.tgtHllType = tgtHllType;
      this.curMode = curMode;
   }

   abstract HllSketchImpl copy();

   abstract HllSketchImpl copyAs(TgtHllType var1);

   abstract HllSketchImpl couponUpdate(int var1);

   abstract int getCompactSerializationBytes();

   abstract double getCompositeEstimate();

   CurMode getCurMode() {
      return this.curMode;
   }

   abstract double getEstimate();

   abstract double getHipEstimate();

   int getLgConfigK() {
      return this.lgConfigK;
   }

   abstract double getLowerBound(int var1);

   abstract int getMemDataStart();

   abstract Memory getMemory();

   abstract int getPreInts();

   TgtHllType getTgtHllType() {
      return this.tgtHllType;
   }

   abstract int getUpdatableSerializationBytes();

   abstract double getUpperBound(int var1);

   abstract WritableMemory getWritableMemory();

   abstract boolean isCompact();

   abstract boolean isEmpty();

   abstract boolean isMemory();

   abstract boolean isOffHeap();

   abstract boolean isOutOfOrder();

   abstract boolean isRebuildCurMinNumKxQFlag();

   abstract boolean isSameResource(Memory var1);

   abstract PairIterator iterator();

   abstract void mergeTo(HllSketch var1);

   abstract void putEmptyFlag(boolean var1);

   abstract void putOutOfOrder(boolean var1);

   abstract void putRebuildCurMinNumKxQFlag(boolean var1);

   abstract HllSketchImpl reset();

   abstract byte[] toCompactByteArray();

   abstract byte[] toUpdatableByteArray();
}
