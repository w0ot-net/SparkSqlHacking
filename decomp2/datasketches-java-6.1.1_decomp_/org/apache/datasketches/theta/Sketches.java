package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class Sketches {
   private Sketches() {
   }

   public static double getEstimate(Memory srcMem) {
      checkIfValidThetaSketch(srcMem);
      return Sketch.estimate(getThetaLong(srcMem), getRetainedEntries(srcMem));
   }

   public static double getLowerBound(int numStdDev, Memory srcMem) {
      return Sketch.lowerBound(getRetainedEntries(srcMem), getThetaLong(srcMem), numStdDev, getEmpty(srcMem));
   }

   public static int getMaxAnotBResultBytes(int maxNomEntries) {
      return SetOperation.getMaxAnotBResultBytes(maxNomEntries);
   }

   public static int getMaxCompactSketchBytes(int numberOfEntries) {
      return Sketch.getMaxCompactSketchBytes(numberOfEntries);
   }

   public static int getCompactSketchMaxBytes(int nomEntries) {
      return Sketch.getCompactSketchMaxBytes(nomEntries);
   }

   public static int getMaxIntersectionBytes(int nomEntries) {
      return SetOperation.getMaxIntersectionBytes(nomEntries);
   }

   public static int getMaxUnionBytes(int nomEntries) {
      return SetOperation.getMaxUnionBytes(nomEntries);
   }

   public static int getMaxUpdateSketchBytes(int nomEntries) {
      return Sketch.getMaxUpdateSketchBytes(nomEntries);
   }

   public static int getSerializationVersion(Memory srcMem) {
      return Sketch.getSerializationVersion(srcMem);
   }

   public static double getUpperBound(int numStdDev, Memory srcMem) {
      return Sketch.upperBound(getRetainedEntries(srcMem), getThetaLong(srcMem), numStdDev, getEmpty(srcMem));
   }

   public static CompactSketch heapifyCompactSketch(Memory srcMem) {
      return CompactSketch.heapify(srcMem);
   }

   public static CompactSketch heapifyCompactSketch(Memory srcMem, long expectedSeed) {
      return CompactSketch.heapify(srcMem, expectedSeed);
   }

   public static CompactSketch wrapCompactSketch(Memory srcMem) {
      return CompactSketch.wrap(srcMem);
   }

   public static CompactSketch wrapCompactSketch(Memory srcMem, long expectedSeed) {
      return CompactSketch.wrap(srcMem, expectedSeed);
   }

   public static SetOperation heapifySetOperation(Memory srcMem) {
      return SetOperation.heapify(srcMem);
   }

   public static SetOperation heapifySetOperation(Memory srcMem, long expectedSeed) {
      return SetOperation.heapify(srcMem, expectedSeed);
   }

   public static Sketch heapifySketch(Memory srcMem) {
      return Sketch.heapify(srcMem);
   }

   public static Sketch heapifySketch(Memory srcMem, long expectedSeed) {
      return Sketch.heapify(srcMem, expectedSeed);
   }

   public static UpdateSketch heapifyUpdateSketch(Memory srcMem) {
      return UpdateSketch.heapify(srcMem);
   }

   public static UpdateSketch heapifyUpdateSketch(Memory srcMem, long expectedSeed) {
      return UpdateSketch.heapify(srcMem, expectedSeed);
   }

   public static SetOperationBuilder setOperationBuilder() {
      return new SetOperationBuilder();
   }

   public static UpdateSketchBuilder updateSketchBuilder() {
      return new UpdateSketchBuilder();
   }

   public static Intersection wrapIntersection(Memory srcMem) {
      return (Intersection)SetOperation.wrap(srcMem);
   }

   public static Intersection wrapIntersection(WritableMemory srcMem) {
      return (Intersection)SetOperation.wrap(srcMem);
   }

   public static SetOperation wrapSetOperation(Memory srcMem) {
      return wrapSetOperation(srcMem, 9001L);
   }

   public static SetOperation wrapSetOperation(Memory srcMem, long expectedSeed) {
      return SetOperation.wrap(srcMem, expectedSeed);
   }

   public static SetOperation wrapSetOperation(WritableMemory srcMem) {
      return wrapSetOperation(srcMem, 9001L);
   }

   public static SetOperation wrapSetOperation(WritableMemory srcMem, long expectedSeed) {
      return SetOperation.wrap(srcMem, expectedSeed);
   }

   public static Sketch wrapSketch(Memory srcMem) {
      return Sketch.wrap(srcMem);
   }

   public static Sketch wrapSketch(Memory srcMem, long expectedSeed) {
      return Sketch.wrap(srcMem, expectedSeed);
   }

   public static Union wrapUnion(Memory srcMem) {
      return (Union)SetOperation.wrap(srcMem);
   }

   public static Union wrapUnion(WritableMemory srcMem) {
      return (Union)SetOperation.wrap(srcMem);
   }

   public static UpdateSketch wrapUpdateSketch(WritableMemory srcMem) {
      return wrapUpdateSketch(srcMem, 9001L);
   }

   public static UpdateSketch wrapUpdateSketch(WritableMemory srcMem, long expectedSeed) {
      return UpdateSketch.wrap(srcMem, expectedSeed);
   }

   static void checkIfValidThetaSketch(Memory srcMem) {
      int fam = srcMem.getByte(2L);
      if (!Sketch.isValidSketchID(fam)) {
         throw new SketchesArgumentException("Source Memory not a valid Sketch. Family: " + Family.idToFamily(fam).toString());
      }
   }

   static boolean getEmpty(Memory srcMem) {
      int serVer = srcMem.getByte(1L);
      if (serVer != 1) {
         return (srcMem.getByte(5L) & 4) != 0;
      } else {
         return getThetaLong(srcMem) == Long.MAX_VALUE && getRetainedEntries(srcMem) == 0;
      }
   }

   static int getPreambleLongs(Memory srcMem) {
      return srcMem.getByte(0L) & 63;
   }

   static int getRetainedEntries(Memory srcMem) {
      int serVer = srcMem.getByte(1L);
      if (serVer == 1) {
         int entries = srcMem.getInt(8L);
         return getThetaLong(srcMem) == Long.MAX_VALUE && entries == 0 ? 0 : entries;
      } else {
         int preLongs = getPreambleLongs(srcMem);
         boolean empty = (srcMem.getByte(5L) & 4) != 0;
         if (preLongs == 1) {
            return empty ? 0 : 1;
         } else {
            return srcMem.getInt(8L);
         }
      }
   }

   static long getThetaLong(Memory srcMem) {
      int preLongs = getPreambleLongs(srcMem);
      return preLongs < 3 ? Long.MAX_VALUE : srcMem.getLong(16L);
   }
}
