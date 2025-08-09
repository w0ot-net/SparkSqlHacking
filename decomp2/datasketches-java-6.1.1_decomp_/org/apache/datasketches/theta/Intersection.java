package org.apache.datasketches.theta;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public abstract class Intersection extends SetOperation {
   public Family getFamily() {
      return Family.INTERSECTION;
   }

   public CompactSketch getResult() {
      return this.getResult(true, (WritableMemory)null);
   }

   public abstract CompactSketch getResult(boolean var1, WritableMemory var2);

   public abstract boolean hasResult();

   public abstract void reset();

   public abstract byte[] toByteArray();

   public abstract void intersect(Sketch var1);

   public CompactSketch intersect(Sketch a, Sketch b) {
      return this.intersect(a, b, true, (WritableMemory)null);
   }

   public abstract CompactSketch intersect(Sketch var1, Sketch var2, boolean var3, WritableMemory var4);

   protected static int getMaxLgArrLongs(Memory dstMem) {
      int preBytes = 24;
      long cap = dstMem.getCapacity();
      return Integer.numberOfTrailingZeros(Util.floorPowerOf2((int)(cap - 24L)) >>> 3);
   }

   protected static void checkMinSizeMemory(Memory mem) {
      int minBytes = 280;
      long cap = mem.getCapacity();
      if (cap < 280L) {
         throw new SketchesArgumentException("Memory must be at least 280 bytes. Actual capacity: " + cap);
      }
   }

   static final long[] compactCachePart(long[] srcCache, int lgArrLongs, int curCount, long thetaLong, boolean dstOrdered) {
      if (curCount == 0) {
         return new long[0];
      } else {
         long[] cacheOut = new long[curCount];
         int len = 1 << lgArrLongs;
         int j = 0;

         for(int i = 0; i < len; ++i) {
            long v = srcCache[i];
            if (v > 0L && v < thetaLong) {
               cacheOut[j++] = v;
            }
         }

         assert curCount == j;

         if (dstOrdered) {
            Arrays.sort(cacheOut);
         }

         return cacheOut;
      }
   }

   protected static void memChecks(Memory srcMem) {
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int famID = PreambleUtil.extractFamilyID(srcMem);
      boolean empty = (PreambleUtil.extractFlags(srcMem) & 4) > 0;
      int curCount = PreambleUtil.extractCurCount(srcMem);
      if (preLongs != 3) {
         throw new SketchesArgumentException("Memory PreambleLongs must equal 3: " + preLongs);
      } else if (serVer != 3) {
         throw new SketchesArgumentException("Serialization Version must equal 3");
      } else {
         Family.INTERSECTION.checkFamilyID(famID);
         if (empty && curCount != 0) {
            throw new SketchesArgumentException("srcMem empty state inconsistent with curCount: " + empty + "," + curCount);
         }
      }
   }
}
