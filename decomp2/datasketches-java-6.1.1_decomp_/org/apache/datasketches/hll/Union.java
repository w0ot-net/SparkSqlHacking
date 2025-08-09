package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class Union extends BaseHllSketch {
   final int lgMaxK;
   private final HllSketch gadget;

   public Union() {
      this.lgMaxK = 12;
      this.gadget = new HllSketch(this.lgMaxK, TgtHllType.HLL_8);
   }

   public Union(int lgMaxK) {
      this.lgMaxK = HllUtil.checkLgK(lgMaxK);
      this.gadget = new HllSketch(lgMaxK, TgtHllType.HLL_8);
   }

   public Union(int lgMaxK, WritableMemory dstWmem) {
      this.lgMaxK = HllUtil.checkLgK(lgMaxK);
      this.gadget = new HllSketch(lgMaxK, TgtHllType.HLL_8, dstWmem);
   }

   private Union(HllSketch sketch) {
      this.lgMaxK = sketch.getLgConfigK();
      this.gadget = sketch;
   }

   public static final Union heapify(byte[] byteArray) {
      return heapify(Memory.wrap(byteArray));
   }

   public static final Union heapify(Memory mem) {
      int lgK = HllUtil.checkLgK(mem.getByte((long)PreambleUtil.LG_K_BYTE));
      HllSketch sk = HllSketch.heapify(mem, false);
      Union union = new Union(lgK);
      union.update(sk);
      return union;
   }

   public static final Union writableWrap(WritableMemory srcWmem) {
      TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(srcWmem);
      if (tgtHllType != TgtHllType.HLL_8) {
         throw new SketchesArgumentException("Union can only wrap writable HLL_8 sketches that were the Gadget of a Union.");
      } else {
         return new Union(HllSketch.writableWrap(srcWmem, false));
      }
   }

   public double getCompositeEstimate() {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.hllSketchImpl.getCompositeEstimate();
   }

   CurMode getCurMode() {
      return this.gadget.getCurMode();
   }

   public int getCompactSerializationBytes() {
      return this.gadget.getCompactSerializationBytes();
   }

   public double getEstimate() {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.getEstimate();
   }

   public int getLgConfigK() {
      return this.gadget.getLgConfigK();
   }

   public double getLowerBound(int numStdDev) {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.getLowerBound(numStdDev);
   }

   public static int getMaxSerializationBytes(int lgK) {
      return HllSketch.getMaxUpdatableSerializationBytes(lgK, TgtHllType.HLL_8);
   }

   public HllSketch getResult() {
      return this.getResult(HllSketch.DEFAULT_HLL_TYPE);
   }

   public HllSketch getResult(TgtHllType tgtHllType) {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.copyAs(tgtHllType);
   }

   public TgtHllType getTgtHllType() {
      return TgtHllType.HLL_8;
   }

   public int getUpdatableSerializationBytes() {
      return this.gadget.getUpdatableSerializationBytes();
   }

   public double getUpperBound(int numStdDev) {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.getUpperBound(numStdDev);
   }

   public boolean isCompact() {
      return this.gadget.isCompact();
   }

   public boolean isEmpty() {
      return this.gadget.isEmpty();
   }

   public boolean isMemory() {
      return this.gadget.isMemory();
   }

   public boolean isOffHeap() {
      return this.gadget.isOffHeap();
   }

   boolean isOutOfOrder() {
      return this.gadget.isOutOfOrder();
   }

   public boolean isSameResource(Memory mem) {
      return this.gadget.isSameResource(mem);
   }

   boolean isRebuildCurMinNumKxQFlag() {
      return this.gadget.hllSketchImpl.isRebuildCurMinNumKxQFlag();
   }

   void putRebuildCurMinNumKxQFlag(boolean rebuild) {
      this.gadget.hllSketchImpl.putRebuildCurMinNumKxQFlag(rebuild);
   }

   public void reset() {
      this.gadget.reset();
   }

   public byte[] toCompactByteArray() {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.toCompactByteArray();
   }

   public byte[] toUpdatableByteArray() {
      checkRebuildCurMinNumKxQ(this.gadget);
      return this.gadget.toUpdatableByteArray();
   }

   public String toString(boolean summary, boolean hllDetail, boolean auxDetail, boolean all) {
      HllSketch clone = this.gadget.copy();
      checkRebuildCurMinNumKxQ(clone);
      return clone.toString(summary, hllDetail, auxDetail, all);
   }

   public void update(HllSketch sketch) {
      this.gadget.hllSketchImpl = unionImpl(sketch, this.gadget, this.lgMaxK);
   }

   void couponUpdate(int coupon) {
      if (coupon != 0) {
         this.gadget.hllSketchImpl = this.gadget.hllSketchImpl.couponUpdate(coupon);
      }
   }

   private static HllSketchImpl unionImpl(HllSketch source, HllSketch gadget, int lgMaxK) {
      assert gadget.getTgtHllType() == TgtHllType.HLL_8;

      if (source != null && !source.isEmpty()) {
         CurMode srcMode = source.getCurMode();
         if (srcMode == CurMode.LIST) {
            source.mergeTo(gadget);
            return gadget.hllSketchImpl;
         } else {
            int srcLgK = source.getLgConfigK();
            int gadgetLgK = gadget.getLgConfigK();
            boolean srcIsMem = source.isMemory();
            boolean gdtIsMem = gadget.isMemory();
            boolean gdtEmpty = gadget.isEmpty();
            if (srcMode == CurMode.SET) {
               if (gdtEmpty && srcLgK == gadgetLgK && !srcIsMem && !gdtIsMem) {
                  gadget.hllSketchImpl = source.copyAs(TgtHllType.HLL_8).hllSketchImpl;
                  return gadget.hllSketchImpl;
               } else {
                  source.mergeTo(gadget);
                  return gadget.hllSketchImpl;
               }
            } else {
               int bit0 = gdtIsMem ? 1 : 0;
               int bits1_2 = (gdtEmpty ? 3 : gadget.getCurMode().ordinal()) << 1;
               int bit3 = srcLgK < gadgetLgK ? 8 : 0;
               int bit4 = srcLgK > lgMaxK ? 16 : 0;
               int sw = bit4 | bit3 | bits1_2 | bit0;
               HllSketchImpl hllSketchImpl = null;
               switch (sw) {
                  case 0:
                  case 2:
                  case 8:
                  case 10:
                     HllSketch srcHll8Heap = source.copyAs(TgtHllType.HLL_8);
                     gadget.mergeTo(srcHll8Heap);
                     hllSketchImpl = srcHll8Heap.hllSketchImpl;
                     break;
                  case 1:
                  case 3:
                  case 9:
                  case 11:
                     HllSketch srcHll8Heap = source.copyAs(TgtHllType.HLL_8);
                     gadget.mergeTo(srcHll8Heap);
                     hllSketchImpl = useGadgetMemory(gadget, srcHll8Heap, false).hllSketchImpl;
                     break;
                  case 4:
                  case 5:
                  case 20:
                  case 21:
                     mergeHlltoHLLmode(source, gadget, srcLgK, gadgetLgK, srcIsMem, gdtIsMem);
                     hllSketchImpl = gadget.putOutOfOrderFlag(true).hllSketchImpl;
                     break;
                  case 6:
                  case 14:
                     HllSketch srcHll8Heap = source.copyAs(TgtHllType.HLL_8);
                     hllSketchImpl = srcHll8Heap.hllSketchImpl;
                     break;
                  case 7:
                  case 15:
                     HllSketch srcHll8Heap = source.copyAs(TgtHllType.HLL_8);
                     hllSketchImpl = useGadgetMemory(gadget, srcHll8Heap, false).hllSketchImpl;
                     break;
                  case 12:
                     HllSketch gdtHll8Heap = downsample(gadget, srcLgK);
                     mergeHlltoHLLmode(source, gdtHll8Heap, srcLgK, gadgetLgK, srcIsMem, false);
                     hllSketchImpl = gdtHll8Heap.putOutOfOrderFlag(true).hllSketchImpl;
                     break;
                  case 13:
                     HllSketch gdtHll8Heap = downsample(gadget, srcLgK);
                     mergeHlltoHLLmode(source, gdtHll8Heap, srcLgK, gadgetLgK, srcIsMem, false);
                     hllSketchImpl = useGadgetMemory(gadget, gdtHll8Heap, true).hllSketchImpl;
                     break;
                  case 16:
                  case 18:
                     HllSketch srcHll8Heap = downsample(source, lgMaxK);
                     gadget.mergeTo(srcHll8Heap);
                     hllSketchImpl = srcHll8Heap.hllSketchImpl;
                     break;
                  case 17:
                  case 19:
                     HllSketch srcHll8Heap = downsample(source, lgMaxK);
                     gadget.mergeTo(srcHll8Heap);
                     hllSketchImpl = useGadgetMemory(gadget, srcHll8Heap, false).hllSketchImpl;
                     break;
                  case 22:
                     HllSketch srcHll8Heap = downsample(source, lgMaxK);
                     hllSketchImpl = srcHll8Heap.hllSketchImpl;
                     break;
                  case 23:
                     HllSketch srcHll8Heap = downsample(source, lgMaxK);
                     hllSketchImpl = useGadgetMemory(gadget, srcHll8Heap, false).hllSketchImpl;
                     break;
                  default:
                     return gadget.hllSketchImpl;
               }

               return hllSketchImpl;
            }
         }
      } else {
         return gadget.hllSketchImpl;
      }
   }

   private static final HllSketch useGadgetMemory(HllSketch gadget, HllSketch hll8Heap, boolean setOooFlag) {
      WritableMemory wmem = gadget.getWritableMemory();
      byte[] byteArr = hll8Heap.toUpdatableByteArray();
      wmem.putByteArray(0L, byteArr, 0, byteArr.length);
      return setOooFlag ? HllSketch.writableWrap(wmem, false).putOutOfOrderFlag(true) : HllSketch.writableWrap(wmem, false);
   }

   private static final void mergeHlltoHLLmode(HllSketch src, HllSketch tgt, int srcLgK, int tgtLgK, boolean srcIsMem, boolean tgtIsMem) {
      int sw = (tgtIsMem ? 1 : 0) | (srcIsMem ? 2 : 0) | (srcLgK > tgtLgK ? 4 : 0) | (src.getTgtHllType() != TgtHllType.HLL_8 ? 8 : 0);
      int srcK = 1 << srcLgK;
      switch (sw) {
         case 0:
            byte[] srcArr = ((Hll8Array)src.hllSketchImpl).hllByteArr;
            byte[] tgtArr = ((Hll8Array)tgt.hllSketchImpl).hllByteArr;

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcArr[i];
               byte tgtV = tgtArr[i];
               tgtArr[i] = (byte)Math.max(srcV, tgtV);
            }
            break;
         case 1:
            byte[] srcArr = ((Hll8Array)src.hllSketchImpl).hllByteArr;
            WritableMemory tgtMem = tgt.getWritableMemory();

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcArr[i];
               byte tgtV = tgtMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               tgtMem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i), (byte)Math.max(srcV, tgtV));
            }
            break;
         case 2:
            Memory srcMem = src.getMemory();
            byte[] tgtArr = ((Hll8Array)tgt.hllSketchImpl).hllByteArr;

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               byte tgtV = tgtArr[i];
               tgtArr[i] = (byte)Math.max(srcV, tgtV);
            }
            break;
         case 3:
            Memory srcMem = src.getMemory();
            WritableMemory tgtMem = tgt.getWritableMemory();

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               byte tgtV = tgtMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               tgtMem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i), (byte)Math.max(srcV, tgtV));
            }
            break;
         case 4:
            int tgtKmask = (1 << tgtLgK) - 1;
            byte[] srcArr = ((Hll8Array)src.hllSketchImpl).hllByteArr;
            byte[] tgtArr = ((Hll8Array)tgt.hllSketchImpl).hllByteArr;

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcArr[i];
               int j = i & tgtKmask;
               byte tgtV = tgtArr[j];
               tgtArr[j] = (byte)Math.max(srcV, tgtV);
            }
            break;
         case 5:
            int tgtKmask = (1 << tgtLgK) - 1;
            byte[] srcArr = ((Hll8Array)src.hllSketchImpl).hllByteArr;
            WritableMemory tgtMem = tgt.getWritableMemory();

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcArr[i];
               int j = i & tgtKmask;
               byte tgtV = tgtMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + j));
               tgtMem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + j), (byte)Math.max(srcV, tgtV));
            }
            break;
         case 6:
            int tgtKmask = (1 << tgtLgK) - 1;
            Memory srcMem = src.getMemory();
            byte[] tgtArr = ((Hll8Array)tgt.hllSketchImpl).hllByteArr;

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               int j = i & tgtKmask;
               byte tgtV = tgtArr[j];
               tgtArr[j] = (byte)Math.max(srcV, tgtV);
            }
            break;
         case 7:
            int tgtKmask = (1 << tgtLgK) - 1;
            Memory srcMem = src.getMemory();
            WritableMemory tgtMem = tgt.getWritableMemory();

            for(int i = 0; i < srcK; ++i) {
               byte srcV = srcMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i));
               int j = i & tgtKmask;
               byte tgtV = tgtMem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + j));
               tgtMem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + j), (byte)Math.max(srcV, tgtV));
            }
            break;
         case 8:
         case 9:
            AbstractHllArray tgtAbsHllArr = (AbstractHllArray)tgt.hllSketchImpl;
            if (src.getTgtHllType() == TgtHllType.HLL_4) {
               Hll4Array src4 = (Hll4Array)src.hllSketchImpl;
               AuxHashMap auxHashMap = src4.getAuxHashMap();
               int curMin = src4.getCurMin();
               int i = 0;

               for(int j = 0; j < srcK; ++j) {
                  byte b = src4.hllByteArr[i++];
                  int value = Byte.toUnsignedInt(b) & 15;
                  tgtAbsHllArr.updateSlotNoKxQ(j, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
                  ++j;
                  value = Byte.toUnsignedInt(b) >>> 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
               }
            } else {
               Hll6Array src6 = (Hll6Array)src.hllSketchImpl;
               int i = 0;
               int j = 0;

               while(j < srcK) {
                  byte b1 = src6.hllByteArr[i++];
                  byte b2 = src6.hllByteArr[i++];
                  byte b3 = src6.hllByteArr[i++];
                  int value = Byte.toUnsignedInt(b1) & 63;
                  tgtAbsHllArr.updateSlotNoKxQ(j++, value);
                  value = Byte.toUnsignedInt(b1) >>> 6;
                  value |= (Byte.toUnsignedInt(b2) & 15) << 2;
                  tgtAbsHllArr.updateSlotNoKxQ(j++, value);
                  value = Byte.toUnsignedInt(b2) >>> 4;
                  value |= (Byte.toUnsignedInt(b3) & 3) << 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j++, value);
                  value = Byte.toUnsignedInt(b3) >>> 2;
                  tgtAbsHllArr.updateSlotNoKxQ(j++, value);
               }
            }
            break;
         case 10:
         case 11:
            AbstractHllArray tgtAbsHllArr = (AbstractHllArray)tgt.hllSketchImpl;
            if (src.getTgtHllType() == TgtHllType.HLL_4) {
               DirectHll4Array src4 = (DirectHll4Array)src.hllSketchImpl;
               AuxHashMap auxHashMap = src4.getAuxHashMap();
               int curMin = src4.getCurMin();
               int i = 0;

               for(int j = 0; j < srcK; ++j) {
                  byte b = src4.mem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i++));
                  int value = Byte.toUnsignedInt(b) & 15;
                  tgtAbsHllArr.updateSlotNoKxQ(j, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
                  ++j;
                  value = Byte.toUnsignedInt(b) >>> 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
               }
            } else {
               DirectHll6Array src6 = (DirectHll6Array)src.hllSketchImpl;
               int i = 0;
               int offset = PreambleUtil.HLL_BYTE_ARR_START;

               while(i < srcK) {
                  byte b1 = src6.mem.getByte((long)(offset++));
                  byte b2 = src6.mem.getByte((long)(offset++));
                  byte b3 = src6.mem.getByte((long)(offset++));
                  int value = Byte.toUnsignedInt(b1) & 63;
                  tgtAbsHllArr.updateSlotNoKxQ(i++, value);
                  value = Byte.toUnsignedInt(b1) >>> 6;
                  value |= (Byte.toUnsignedInt(b2) & 15) << 2;
                  tgtAbsHllArr.updateSlotNoKxQ(i++, value);
                  value = Byte.toUnsignedInt(b2) >>> 4;
                  value |= (Byte.toUnsignedInt(b3) & 3) << 4;
                  tgtAbsHllArr.updateSlotNoKxQ(i++, value);
                  value = Byte.toUnsignedInt(b3) >>> 2;
                  tgtAbsHllArr.updateSlotNoKxQ(i++, value);
               }
            }
            break;
         case 12:
         case 13:
            int tgtKmask = (1 << tgtLgK) - 1;
            AbstractHllArray tgtAbsHllArr = (AbstractHllArray)tgt.hllSketchImpl;
            if (src.getTgtHllType() == TgtHllType.HLL_4) {
               Hll4Array src4 = (Hll4Array)src.hllSketchImpl;
               AuxHashMap auxHashMap = src4.getAuxHashMap();
               int curMin = src4.getCurMin();
               int i = 0;

               for(int j = 0; j < srcK; ++j) {
                  byte b = src4.hllByteArr[i++];
                  int value = Byte.toUnsignedInt(b) & 15;
                  tgtAbsHllArr.updateSlotNoKxQ(j & tgtKmask, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
                  ++j;
                  value = Byte.toUnsignedInt(b) >>> 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j & tgtKmask, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
               }
            } else {
               Hll6Array src6 = (Hll6Array)src.hllSketchImpl;
               int i = 0;
               int j = 0;

               while(j < srcK) {
                  byte b1 = src6.hllByteArr[i++];
                  byte b2 = src6.hllByteArr[i++];
                  byte b3 = src6.hllByteArr[i++];
                  int value = Byte.toUnsignedInt(b1) & 63;
                  tgtAbsHllArr.updateSlotNoKxQ(j++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b1) >>> 6;
                  value |= (Byte.toUnsignedInt(b2) & 15) << 2;
                  tgtAbsHllArr.updateSlotNoKxQ(j++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b2) >>> 4;
                  value |= (Byte.toUnsignedInt(b3) & 3) << 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b3) >>> 2;
                  tgtAbsHllArr.updateSlotNoKxQ(j++ & tgtKmask, value);
               }
            }
            break;
         case 14:
         case 15:
            int tgtKmask = (1 << tgtLgK) - 1;
            AbstractHllArray tgtAbsHllArr = (AbstractHllArray)tgt.hllSketchImpl;
            if (src.getTgtHllType() == TgtHllType.HLL_4) {
               DirectHll4Array src4 = (DirectHll4Array)src.hllSketchImpl;
               AuxHashMap auxHashMap = src4.getAuxHashMap();
               int curMin = src4.getCurMin();
               int i = 0;

               for(int j = 0; j < srcK; ++j) {
                  byte b = src4.mem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + i++));
                  int value = Byte.toUnsignedInt(b) & 15;
                  tgtAbsHllArr.updateSlotNoKxQ(j & tgtKmask, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
                  ++j;
                  value = Byte.toUnsignedInt(b) >>> 4;
                  tgtAbsHllArr.updateSlotNoKxQ(j & tgtKmask, value == 15 ? auxHashMap.mustFindValueFor(j) : value + curMin);
               }
            } else {
               DirectHll6Array src6 = (DirectHll6Array)src.hllSketchImpl;
               int i = 0;
               int offset = PreambleUtil.HLL_BYTE_ARR_START;

               while(i < srcK) {
                  byte b1 = src6.mem.getByte((long)(offset++));
                  byte b2 = src6.mem.getByte((long)(offset++));
                  byte b3 = src6.mem.getByte((long)(offset++));
                  int value = Byte.toUnsignedInt(b1) & 63;
                  tgtAbsHllArr.updateSlotNoKxQ(i++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b1) >>> 6;
                  value |= (Byte.toUnsignedInt(b2) & 15) << 2;
                  tgtAbsHllArr.updateSlotNoKxQ(i++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b2) >>> 4;
                  value |= (Byte.toUnsignedInt(b3) & 3) << 4;
                  tgtAbsHllArr.updateSlotNoKxQ(i++ & tgtKmask, value);
                  value = Byte.toUnsignedInt(b3) >>> 2;
                  tgtAbsHllArr.updateSlotNoKxQ(i++ & tgtKmask, value);
               }
            }
      }

      tgt.hllSketchImpl.putRebuildCurMinNumKxQFlag(true);
   }

   private static final HllSketch downsample(HllSketch candidate, int tgtLgK) {
      AbstractHllArray candArr = (AbstractHllArray)candidate.hllSketchImpl;
      HllArray tgtHllArr = HllArray.newHeapHll(tgtLgK, TgtHllType.HLL_8);
      PairIterator candItr = candArr.iterator();

      while(candItr.nextValid()) {
         tgtHllArr.couponUpdate(candItr.getPair());
      }

      tgtHllArr.putHipAccum(candArr.getHipAccum());
      tgtHllArr.putOutOfOrder(candidate.isOutOfOrder());
      tgtHllArr.putRebuildCurMinNumKxQFlag(false);
      return new HllSketch(tgtHllArr);
   }

   static final void checkRebuildCurMinNumKxQ(HllSketch sketch) {
      HllSketchImpl hllSketchImpl = sketch.hllSketchImpl;
      CurMode curMode = sketch.getCurMode();
      TgtHllType tgtHllType = sketch.getTgtHllType();
      boolean rebuild = hllSketchImpl.isRebuildCurMinNumKxQFlag();
      if (rebuild && curMode == CurMode.HLL && tgtHllType == TgtHllType.HLL_8) {
         AbstractHllArray absHllArr = (AbstractHllArray)hllSketchImpl;
         int curMin = 64;
         int numAtCurMin = 0;
         double kxq0 = (double)(1 << absHllArr.getLgConfigK());
         double kxq1 = (double)0.0F;
         PairIterator itr = absHllArr.iterator();

         while(itr.nextAll()) {
            int v = itr.getValue();
            if (v > 0) {
               if (v < 32) {
                  kxq0 += Util.invPow2(v) - (double)1.0F;
               } else {
                  kxq1 += Util.invPow2(v) - (double)1.0F;
               }
            }

            if (v <= curMin) {
               if (v < curMin) {
                  curMin = v;
                  numAtCurMin = 1;
               } else {
                  ++numAtCurMin;
               }
            }
         }

         absHllArr.putKxQ0(kxq0);
         absHllArr.putKxQ1(kxq1);
         absHllArr.putCurMin(curMin);
         absHllArr.putNumAtCurMin(numAtCurMin);
         absHllArr.putRebuildCurMinNumKxQFlag(false);
      }
   }
}
