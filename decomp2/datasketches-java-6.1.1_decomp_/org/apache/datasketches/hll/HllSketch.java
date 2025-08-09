package org.apache.datasketches.hll;

import java.util.Objects;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class HllSketch extends BaseHllSketch {
   public static final int DEFAULT_LG_K = 12;
   public static final TgtHllType DEFAULT_HLL_TYPE;
   HllSketchImpl hllSketchImpl;

   public HllSketch() {
      this(12, DEFAULT_HLL_TYPE);
   }

   public HllSketch(int lgConfigK) {
      this(lgConfigK, DEFAULT_HLL_TYPE);
   }

   public HllSketch(int lgConfigK, TgtHllType tgtHllType) {
      this.hllSketchImpl = null;
      this.hllSketchImpl = new CouponList(HllUtil.checkLgK(lgConfigK), tgtHllType, CurMode.LIST);
   }

   public HllSketch(int lgConfigK, TgtHllType tgtHllType, WritableMemory dstMem) {
      this.hllSketchImpl = null;
      Objects.requireNonNull(dstMem, "Destination Memory must not be null");
      long minBytes = (long)getMaxUpdatableSerializationBytes(lgConfigK, tgtHllType);
      long capBytes = dstMem.getCapacity();
      HllUtil.checkMemSize(minBytes, capBytes);
      dstMem.clear(0L, minBytes);
      this.hllSketchImpl = DirectCouponList.newInstance(lgConfigK, tgtHllType, dstMem);
   }

   HllSketch(HllSketch that) {
      this.hllSketchImpl = null;
      this.hllSketchImpl = that.hllSketchImpl.copy();
   }

   HllSketch(HllSketchImpl that) {
      this.hllSketchImpl = null;
      this.hllSketchImpl = that;
   }

   public static final HllSketch heapify(byte[] byteArray) {
      return heapify(Memory.wrap(byteArray));
   }

   public static final HllSketch heapify(Memory srcMem) {
      return heapify(srcMem, true);
   }

   static final HllSketch heapify(Memory srcMem, boolean checkRebuild) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null");
      Util.checkBounds(0L, 8L, srcMem.getCapacity());
      CurMode curMode = HllUtil.checkPreamble(srcMem);
      HllSketch heapSketch;
      if (curMode == CurMode.HLL) {
         TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(srcMem);
         if (tgtHllType == TgtHllType.HLL_4) {
            heapSketch = new HllSketch(Hll4Array.heapify(srcMem));
         } else if (tgtHllType == TgtHllType.HLL_6) {
            heapSketch = new HllSketch(Hll6Array.heapify(srcMem));
         } else {
            heapSketch = new HllSketch(Hll8Array.heapify(srcMem));
            if (checkRebuild) {
               Union.checkRebuildCurMinNumKxQ(heapSketch);
            }
         }
      } else if (curMode == CurMode.LIST) {
         heapSketch = new HllSketch(CouponList.heapifyList(srcMem));
      } else {
         heapSketch = new HllSketch(CouponHashSet.heapifySet(srcMem));
      }

      return heapSketch;
   }

   public static final HllSketch writableWrap(WritableMemory srcWmem) {
      return writableWrap(srcWmem, true);
   }

   static final HllSketch writableWrap(WritableMemory srcWmem, boolean checkRebuild) {
      Objects.requireNonNull(srcWmem, "Source Memory must not be null");
      Util.checkBounds(0L, 8L, srcWmem.getCapacity());
      if (PreambleUtil.extractCompactFlag(srcWmem)) {
         throw new SketchesArgumentException("Cannot perform a writableWrap of a writable sketch image that is in compact form. Compact sketches are by definition immutable.");
      } else {
         int lgConfigK = PreambleUtil.extractLgK(srcWmem);
         TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(srcWmem);
         long minBytes = (long)getMaxUpdatableSerializationBytes(lgConfigK, tgtHllType);
         long capBytes = srcWmem.getCapacity();
         HllUtil.checkMemSize(minBytes, capBytes);
         CurMode curMode = HllUtil.checkPreamble(srcWmem);
         HllSketch directSketch;
         if (curMode == CurMode.HLL) {
            if (tgtHllType == TgtHllType.HLL_4) {
               directSketch = new HllSketch(new DirectHll4Array(lgConfigK, srcWmem));
            } else if (tgtHllType == TgtHllType.HLL_6) {
               directSketch = new HllSketch(new DirectHll6Array(lgConfigK, srcWmem));
            } else {
               directSketch = new HllSketch(new DirectHll8Array(lgConfigK, srcWmem));
               if (checkRebuild) {
                  Union.checkRebuildCurMinNumKxQ(directSketch);
               }
            }
         } else if (curMode == CurMode.LIST) {
            directSketch = new HllSketch(new DirectCouponList(lgConfigK, tgtHllType, curMode, srcWmem));
         } else {
            directSketch = new HllSketch(new DirectCouponHashSet(lgConfigK, tgtHllType, srcWmem));
         }

         return directSketch;
      }
   }

   public static final HllSketch wrap(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null");
      Util.checkBounds(0L, 8L, srcMem.getCapacity());
      int lgConfigK = PreambleUtil.extractLgK(srcMem);
      TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(srcMem);
      CurMode curMode = HllUtil.checkPreamble(srcMem);
      HllSketch directSketch;
      if (curMode == CurMode.HLL) {
         if (tgtHllType == TgtHllType.HLL_4) {
            directSketch = new HllSketch(new DirectHll4Array(lgConfigK, srcMem));
         } else if (tgtHllType == TgtHllType.HLL_6) {
            directSketch = new HllSketch(new DirectHll6Array(lgConfigK, srcMem));
         } else {
            directSketch = new HllSketch(new DirectHll8Array(lgConfigK, srcMem));
            Union.checkRebuildCurMinNumKxQ(directSketch);
         }
      } else if (curMode == CurMode.LIST) {
         directSketch = new HllSketch(new DirectCouponList(lgConfigK, tgtHllType, curMode, srcMem));
      } else {
         directSketch = new HllSketch(new DirectCouponHashSet(lgConfigK, tgtHllType, srcMem));
      }

      return directSketch;
   }

   public HllSketch copy() {
      return new HllSketch(this);
   }

   public HllSketch copyAs(TgtHllType tgtHllType) {
      return new HllSketch(this.hllSketchImpl.copyAs(tgtHllType));
   }

   public double getCompositeEstimate() {
      return this.hllSketchImpl.getCompositeEstimate();
   }

   public double getEstimate() {
      return this.hllSketchImpl.getEstimate();
   }

   double getHipEstimate() {
      return this.hllSketchImpl.getHipEstimate();
   }

   public int getLgConfigK() {
      return this.hllSketchImpl.getLgConfigK();
   }

   public int getCompactSerializationBytes() {
      return this.hllSketchImpl.getCompactSerializationBytes();
   }

   public double getLowerBound(int numStdDev) {
      return this.hllSketchImpl.getLowerBound(numStdDev);
   }

   public static final int getMaxUpdatableSerializationBytes(int lgConfigK, TgtHllType tgtHllType) {
      int arrBytes;
      if (tgtHllType == TgtHllType.HLL_4) {
         int auxBytes = 4 << HllUtil.LG_AUX_ARR_INTS[lgConfigK];
         arrBytes = AbstractHllArray.hll4ArrBytes(lgConfigK) + auxBytes;
      } else if (tgtHllType == TgtHllType.HLL_6) {
         arrBytes = AbstractHllArray.hll6ArrBytes(lgConfigK);
      } else {
         arrBytes = AbstractHllArray.hll8ArrBytes(lgConfigK);
      }

      return PreambleUtil.HLL_BYTE_ARR_START + arrBytes;
   }

   Memory getMemory() {
      return this.hllSketchImpl.getMemory();
   }

   public TgtHllType getTgtHllType() {
      return this.hllSketchImpl.getTgtHllType();
   }

   public int getUpdatableSerializationBytes() {
      return this.hllSketchImpl.getUpdatableSerializationBytes();
   }

   WritableMemory getWritableMemory() {
      return this.hllSketchImpl.getWritableMemory();
   }

   public double getUpperBound(int numStdDev) {
      return this.hllSketchImpl.getUpperBound(numStdDev);
   }

   public boolean isCompact() {
      return this.hllSketchImpl.isCompact();
   }

   public boolean isEmpty() {
      return this.hllSketchImpl.isEmpty();
   }

   public boolean isMemory() {
      return this.hllSketchImpl.isMemory();
   }

   public boolean isOffHeap() {
      return this.hllSketchImpl.isOffHeap();
   }

   boolean isOutOfOrder() {
      return this.hllSketchImpl.isOutOfOrder();
   }

   public boolean isSameResource(Memory mem) {
      return this.hllSketchImpl.isSameResource(mem);
   }

   void mergeTo(HllSketch that) {
      this.hllSketchImpl.mergeTo(that);
   }

   HllSketch putOutOfOrderFlag(boolean oooFlag) {
      this.hllSketchImpl.putOutOfOrder(oooFlag);
      return this;
   }

   public void reset() {
      this.hllSketchImpl = this.hllSketchImpl.reset();
   }

   public byte[] toCompactByteArray() {
      return this.hllSketchImpl.toCompactByteArray();
   }

   public byte[] toUpdatableByteArray() {
      return this.hllSketchImpl.toUpdatableByteArray();
   }

   public String toString(boolean summary, boolean detail, boolean auxDetail, boolean all) {
      StringBuilder sb = new StringBuilder();
      if (summary) {
         sb.append("### HLL SKETCH SUMMARY: ").append(Util.LS);
         sb.append("  Log Config K   : ").append(this.getLgConfigK()).append(Util.LS);
         sb.append("  Hll Target     : ").append(this.getTgtHllType()).append(Util.LS);
         sb.append("  Current Mode   : ").append(this.getCurMode()).append(Util.LS);
         sb.append("  Memory         : ").append(this.isMemory()).append(Util.LS);
         sb.append("  LB             : ").append(this.getLowerBound(1)).append(Util.LS);
         sb.append("  Estimate       : ").append(this.getEstimate()).append(Util.LS);
         sb.append("  UB             : ").append(this.getUpperBound(1)).append(Util.LS);
         sb.append("  OutOfOrder Flag: ").append(this.isOutOfOrder()).append(Util.LS);
         if (this.getCurMode() == CurMode.HLL) {
            AbstractHllArray absHll = (AbstractHllArray)this.hllSketchImpl;
            sb.append("  CurMin         : ").append(absHll.getCurMin()).append(Util.LS);
            sb.append("  NumAtCurMin    : ").append(absHll.getNumAtCurMin()).append(Util.LS);
            sb.append("  HipAccum       : ").append(absHll.getHipAccum()).append(Util.LS);
            sb.append("  KxQ0           : ").append(absHll.getKxQ0()).append(Util.LS);
            sb.append("  KxQ1           : ").append(absHll.getKxQ1()).append(Util.LS);
            sb.append("  Rebuild KxQ Flg: ").append(absHll.isRebuildCurMinNumKxQFlag()).append(Util.LS);
         } else {
            sb.append("  Coupon Count   : ").append(((AbstractCoupons)this.hllSketchImpl).getCouponCount()).append(Util.LS);
         }
      }

      if (detail) {
         sb.append("### HLL SKETCH DATA DETAIL: ").append(Util.LS);
         PairIterator pitr = this.iterator();
         sb.append(pitr.getHeader()).append(Util.LS);
         if (all) {
            while(pitr.nextAll()) {
               sb.append(pitr.getString()).append(Util.LS);
            }
         } else {
            while(pitr.nextValid()) {
               sb.append(pitr.getString()).append(Util.LS);
            }
         }
      }

      if (auxDetail && this.getCurMode() == CurMode.HLL && this.getTgtHllType() == TgtHllType.HLL_4) {
         AbstractHllArray absHll = (AbstractHllArray)this.hllSketchImpl;
         PairIterator auxItr = absHll.getAuxIterator();
         if (auxItr != null) {
            sb.append("### HLL SKETCH AUX DETAIL: ").append(Util.LS);
            sb.append(auxItr.getHeader()).append(Util.LS);
            if (all) {
               while(auxItr.nextAll()) {
                  sb.append(auxItr.getString()).append(Util.LS);
               }
            } else {
               while(auxItr.nextValid()) {
                  sb.append(auxItr.getString()).append(Util.LS);
               }
            }
         }
      }

      return sb.toString();
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.toString(byteArr);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.toString(mem);
   }

   PairIterator iterator() {
      return this.hllSketchImpl.iterator();
   }

   CurMode getCurMode() {
      return this.hllSketchImpl.getCurMode();
   }

   void couponUpdate(int coupon) {
      if (coupon >>> 26 != 0) {
         this.hllSketchImpl = this.hllSketchImpl.couponUpdate(coupon);
      }
   }

   static {
      DEFAULT_HLL_TYPE = TgtHllType.HLL_4;
   }
}
