package org.apache.datasketches.theta;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.QuickSelect;

final class Rebuilder {
   private Rebuilder() {
   }

   static final void quickSelectAndRebuild(WritableMemory mem, int preambleLongs, int lgNomLongs) {
      int lgArrLongs = PreambleUtil.extractLgArrLongs(mem);
      int curCount = PreambleUtil.extractCurCount(mem);
      int arrLongs = 1 << lgArrLongs;
      long[] tmpArr = new long[arrLongs];
      int preBytes = preambleLongs << 3;
      mem.getLongArray((long)preBytes, tmpArr, 0, arrLongs);
      int pivot = (1 << lgNomLongs) + 1;
      long newThetaLong = QuickSelect.selectExcludingZeros(tmpArr, curCount, pivot);
      PreambleUtil.insertThetaLong(mem, newThetaLong);
      long[] tgtArr = new long[arrLongs];
      int newCurCount = HashOperations.hashArrayInsert(tmpArr, tgtArr, lgArrLongs, newThetaLong);
      PreambleUtil.insertCurCount(mem, newCurCount);
      mem.putLongArray((long)preBytes, tgtArr, 0, arrLongs);
   }

   static final void moveAndResize(Memory srcMem, int preambleLongs, int srcLgArrLongs, WritableMemory dstMem, int dstLgArrLongs, long thetaLong) {
      int preBytes = preambleLongs << 3;
      srcMem.copyTo(0L, dstMem, 0L, (long)preBytes);
      int srcHTLen = 1 << srcLgArrLongs;
      long[] srcHTArr = new long[srcHTLen];
      srcMem.getLongArray((long)preBytes, srcHTArr, 0, srcHTLen);
      int dstHTLen = 1 << dstLgArrLongs;
      long[] dstHTArr = new long[dstHTLen];
      HashOperations.hashArrayInsert(srcHTArr, dstHTArr, dstLgArrLongs, thetaLong);
      dstMem.putLongArray((long)preBytes, dstHTArr, 0, dstHTLen);
      dstMem.putByte(4L, (byte)dstLgArrLongs);
   }

   static final void resize(WritableMemory mem, int preambleLongs, int srcLgArrLongs, int tgtLgArrLongs) {
      int preBytes = preambleLongs << 3;
      int srcHTLen = 1 << srcLgArrLongs;
      long[] srcHTArr = new long[srcHTLen];
      mem.getLongArray((long)preBytes, srcHTArr, 0, srcHTLen);
      int dstHTLen = 1 << tgtLgArrLongs;
      long[] dstHTArr = new long[dstHTLen];
      long thetaLong = PreambleUtil.extractThetaLong(mem);
      HashOperations.hashArrayInsert(srcHTArr, dstHTArr, tgtLgArrLongs, thetaLong);
      mem.putLongArray((long)preBytes, dstHTArr, 0, dstHTLen);
      PreambleUtil.insertLgArrLongs(mem, tgtLgArrLongs);
   }

   static final int actLgResizeFactor(long capBytes, int lgArrLongs, int preLongs, int lgRF) {
      int maxHTLongs = Util.floorPowerOf2((int)(capBytes >>> 3) - preLongs);
      int lgFactor = Math.max(Integer.numberOfTrailingZeros(maxHTLongs) - lgArrLongs, 0);
      return lgFactor >= lgRF ? lgRF : lgFactor;
   }
}
