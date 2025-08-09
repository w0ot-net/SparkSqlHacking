package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class ToByteArrayImpl {
   static final byte[] toHllByteArray(AbstractHllArray impl, boolean compact) {
      int auxBytes = 0;
      if (impl.tgtHllType == TgtHllType.HLL_4) {
         AuxHashMap auxHashMap = impl.getAuxHashMap();
         if (auxHashMap != null) {
            auxBytes = compact ? auxHashMap.getCompactSizeBytes() : auxHashMap.getUpdatableSizeBytes();
         } else {
            auxBytes = compact ? 0 : 4 << HllUtil.LG_AUX_ARR_INTS[impl.lgConfigK];
         }
      }

      int totBytes = PreambleUtil.HLL_BYTE_ARR_START + impl.getHllByteArrBytes() + auxBytes;
      byte[] byteArr = new byte[totBytes];
      WritableMemory wmem = WritableMemory.writableWrap(byteArr);
      insertHll(impl, wmem, compact);
      return byteArr;
   }

   private static final void insertHll(AbstractHllArray impl, WritableMemory wmem, boolean compact) {
      insertCommonHll(impl, wmem, compact);
      byte[] hllByteArr = ((HllArray)impl).hllByteArr;
      wmem.putByteArray((long)PreambleUtil.HLL_BYTE_ARR_START, hllByteArr, 0, hllByteArr.length);
      if (impl.getAuxHashMap() != null) {
         insertAux(impl, wmem, compact);
      } else {
         wmem.putInt((long)PreambleUtil.AUX_COUNT_INT, 0);
      }

   }

   private static final void insertCommonHll(AbstractHllArray srcImpl, WritableMemory tgtWmem, boolean compact) {
      PreambleUtil.insertPreInts(tgtWmem, srcImpl.getPreInts());
      PreambleUtil.insertSerVer(tgtWmem);
      PreambleUtil.insertFamilyId(tgtWmem);
      PreambleUtil.insertLgK(tgtWmem, srcImpl.getLgConfigK());
      PreambleUtil.insertEmptyFlag(tgtWmem, srcImpl.isEmpty());
      PreambleUtil.insertCompactFlag(tgtWmem, compact);
      PreambleUtil.insertOooFlag(tgtWmem, srcImpl.isOutOfOrder());
      PreambleUtil.insertCurMin(tgtWmem, srcImpl.getCurMin());
      PreambleUtil.insertCurMode(tgtWmem, srcImpl.getCurMode());
      PreambleUtil.insertTgtHllType(tgtWmem, srcImpl.getTgtHllType());
      PreambleUtil.insertHipAccum(tgtWmem, srcImpl.getHipAccum());
      PreambleUtil.insertKxQ0(tgtWmem, srcImpl.getKxQ0());
      PreambleUtil.insertKxQ1(tgtWmem, srcImpl.getKxQ1());
      PreambleUtil.insertNumAtCurMin(tgtWmem, srcImpl.getNumAtCurMin());
      PreambleUtil.insertRebuildCurMinNumKxQFlag(tgtWmem, srcImpl.isRebuildCurMinNumKxQFlag());
   }

   private static final void insertAux(AbstractHllArray srcImpl, WritableMemory tgtWmem, boolean tgtCompact) {
      AuxHashMap auxHashMap = srcImpl.getAuxHashMap();
      int auxCount = auxHashMap.getAuxCount();
      PreambleUtil.insertAuxCount(tgtWmem, auxCount);
      PreambleUtil.insertLgArr(tgtWmem, auxHashMap.getLgAuxArrInts());
      long auxStart = (long)srcImpl.auxStart;
      if (tgtCompact) {
         PairIterator itr = auxHashMap.getIterator();
         int cnt = 0;

         while(itr.nextValid()) {
            PreambleUtil.insertInt(tgtWmem, auxStart + (long)(cnt++ << 2), itr.getPair());
         }

         assert cnt == auxCount;
      } else {
         int auxInts = 1 << auxHashMap.getLgAuxArrInts();
         int[] auxArr = auxHashMap.getAuxIntArr();
         tgtWmem.putIntArray(auxStart, auxArr, 0, auxInts);
      }

   }

   static final byte[] toCouponByteArray(AbstractCoupons impl, boolean dstCompact) {
      int srcCouponCount = impl.getCouponCount();
      int srcLgCouponArrInts = impl.getLgCouponArrInts();
      int srcCouponArrInts = 1 << srcLgCouponArrInts;
      boolean list = impl.getCurMode() == CurMode.LIST;
      int sw = (impl.isMemory() ? 0 : 4) | (impl.isCompact() ? 0 : 2) | (dstCompact ? 0 : 1);
      byte[] byteArrOut;
      switch (sw) {
         case 0:
            Memory srcMem = impl.getMemory();
            int bytesOut = impl.getMemDataStart() + (srcCouponCount << 2);
            byteArrOut = new byte[bytesOut];
            srcMem.getByteArray(0L, byteArrOut, 0, bytesOut);
            break;
         case 1:
            int dataStart = impl.getMemDataStart();
            int bytesOut = dataStart + (srcCouponArrInts << 2);
            byteArrOut = new byte[bytesOut];
            WritableMemory memOut = WritableMemory.writableWrap(byteArrOut);
            copyCommonListAndSet(impl, memOut);
            PreambleUtil.insertCompactFlag(memOut, dstCompact);
            int[] tgtCouponIntArr = new int[srcCouponArrInts];

            int pair;
            int idx;
            for(PairIterator itr = impl.iterator(); itr.nextValid(); tgtCouponIntArr[~idx] = pair) {
               pair = itr.getPair();
               idx = AbstractCoupons.find(tgtCouponIntArr, srcLgCouponArrInts, pair);
               if (idx >= 0) {
                  throw new SketchesStateException("Error: found duplicate.");
               }
            }

            memOut.putIntArray((long)dataStart, tgtCouponIntArr, 0, srcCouponArrInts);
            if (list) {
               PreambleUtil.insertListCount(memOut, srcCouponCount);
            } else {
               PreambleUtil.insertHashSetCount(memOut, srcCouponCount);
            }
            break;
         case 2:
         case 6:
            int dataStart = impl.getMemDataStart();
            int bytesOut = dataStart + (srcCouponCount << 2);
            byteArrOut = new byte[bytesOut];
            WritableMemory memOut = WritableMemory.writableWrap(byteArrOut);
            copyCommonListAndSet(impl, memOut);
            PreambleUtil.insertCompactFlag(memOut, dstCompact);
            PairIterator itr = impl.iterator();
            int cnt = 0;

            while(itr.nextValid()) {
               PreambleUtil.insertInt(memOut, (long)(dataStart + (cnt++ << 2)), itr.getPair());
            }

            if (list) {
               PreambleUtil.insertListCount(memOut, srcCouponCount);
            } else {
               PreambleUtil.insertHashSetCount(memOut, srcCouponCount);
            }
            break;
         case 3:
            Memory srcMem = impl.getMemory();
            int bytesOut = impl.getMemDataStart() + (srcCouponArrInts << 2);
            byteArrOut = new byte[bytesOut];
            srcMem.getByteArray(0L, byteArrOut, 0, bytesOut);
            break;
         case 4:
         case 5:
         default:
            throw new SketchesStateException("Corruption, should not happen: " + sw);
         case 7:
            int dataStart = impl.getMemDataStart();
            int bytesOut = dataStart + (srcCouponArrInts << 2);
            byteArrOut = new byte[bytesOut];
            WritableMemory memOut = WritableMemory.writableWrap(byteArrOut);
            copyCommonListAndSet(impl, memOut);
            memOut.putIntArray((long)dataStart, impl.getCouponIntArr(), 0, srcCouponArrInts);
            if (list) {
               PreambleUtil.insertListCount(memOut, srcCouponCount);
            } else {
               PreambleUtil.insertHashSetCount(memOut, srcCouponCount);
            }
      }

      return byteArrOut;
   }

   private static final void copyCommonListAndSet(AbstractCoupons impl, WritableMemory wmem) {
      PreambleUtil.insertPreInts(wmem, impl.getPreInts());
      PreambleUtil.insertSerVer(wmem);
      PreambleUtil.insertFamilyId(wmem);
      PreambleUtil.insertLgK(wmem, impl.getLgConfigK());
      PreambleUtil.insertLgArr(wmem, impl.getLgCouponArrInts());
      PreambleUtil.insertEmptyFlag(wmem, impl.isEmpty());
      PreambleUtil.insertOooFlag(wmem, impl.isOutOfOrder());
      PreambleUtil.insertCurMode(wmem, impl.getCurMode());
      PreambleUtil.insertTgtHllType(wmem, impl.getTgtHllType());
   }
}
