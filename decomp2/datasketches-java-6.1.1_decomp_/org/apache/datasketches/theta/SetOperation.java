package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.MemoryStatus;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public abstract class SetOperation implements MemoryStatus {
   static final int CONST_PREAMBLE_LONGS = 3;

   SetOperation() {
   }

   public static final SetOperationBuilder builder() {
      return new SetOperationBuilder();
   }

   public static SetOperation heapify(Memory srcMem) {
      return heapify(srcMem, 9001L);
   }

   public static SetOperation heapify(Memory srcMem, long expectedSeed) {
      byte famID = srcMem.getByte(2L);
      Family family = Family.idToFamily(famID);
      switch (family) {
         case UNION:
            return UnionImpl.heapifyInstance(srcMem, expectedSeed);
         case INTERSECTION:
            return IntersectionImpl.heapifyInstance(srcMem, expectedSeed);
         default:
            throw new SketchesArgumentException("SetOperation cannot heapify family: " + family.toString());
      }
   }

   public static SetOperation wrap(Memory srcMem) {
      return wrap(srcMem, 9001L);
   }

   public static SetOperation wrap(Memory srcMem, long expectedSeed) {
      byte famID = srcMem.getByte(2L);
      Family family = Family.idToFamily(famID);
      int serVer = srcMem.getByte(1L);
      if (serVer != 3) {
         throw new SketchesArgumentException("SerVer must be 3: " + serVer);
      } else {
         switch (family) {
            case UNION:
               return UnionImpl.wrapInstance(srcMem, expectedSeed);
            case INTERSECTION:
               return IntersectionImpl.wrapInstance((WritableMemory)srcMem, expectedSeed, true);
            default:
               throw new SketchesArgumentException("SetOperation cannot wrap family: " + family.toString());
         }
      }
   }

   public static SetOperation wrap(WritableMemory srcMem) {
      return wrap(srcMem, 9001L);
   }

   public static SetOperation wrap(WritableMemory srcMem, long expectedSeed) {
      byte famID = srcMem.getByte(2L);
      Family family = Family.idToFamily(famID);
      int serVer = srcMem.getByte(1L);
      if (serVer != 3) {
         throw new SketchesArgumentException("SerVer must be 3: " + serVer);
      } else {
         switch (family) {
            case UNION:
               return UnionImpl.wrapInstance(srcMem, expectedSeed);
            case INTERSECTION:
               return IntersectionImpl.wrapInstance(srcMem, expectedSeed, false);
            default:
               throw new SketchesArgumentException("SetOperation cannot wrap family: " + family.toString());
         }
      }
   }

   public static int getMaxUnionBytes(int nomEntries) {
      int nomEnt = Util.ceilingPowerOf2(nomEntries);
      return (nomEnt << 4) + (Family.UNION.getMaxPreLongs() << 3);
   }

   public static int getMaxIntersectionBytes(int nomEntries) {
      int nomEnt = Util.ceilingPowerOf2(nomEntries);
      int bytes = (nomEnt << 4) + (Family.INTERSECTION.getMaxPreLongs() << 3);
      return bytes;
   }

   public static int getMaxAnotBResultBytes(int nomEntries) {
      int ceil = Util.ceilingPowerOf2(nomEntries);
      return 24 + 15 * ceil;
   }

   public abstract Family getFamily();

   abstract long[] getCache();

   abstract int getRetainedEntries();

   abstract short getSeedHash();

   abstract long getThetaLong();

   abstract boolean isEmpty();
}
