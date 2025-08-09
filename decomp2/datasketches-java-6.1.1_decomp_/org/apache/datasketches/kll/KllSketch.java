package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Random;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.QuantilesAPI;

public abstract class KllSketch implements QuantilesAPI {
   public static final int DEFAULT_K = 200;
   public static final int MAX_K = 65535;
   static final int DEFAULT_M = 8;
   static final int MAX_M = 8;
   static final int MIN_M = 2;
   static final Random random = new Random();
   final SketchType sketchType;
   final SketchStructure sketchStructure;
   boolean readOnly;
   int[] levelsArr;

   KllSketch(SketchType sketchType, SketchStructure sketchStructure) {
      this.sketchType = sketchType;
      this.sketchStructure = sketchStructure;
   }

   abstract String getItemAsString(int var1);

   public static int getKFromEpsilon(double epsilon, boolean pmf) {
      return KllHelper.getKFromEpsilon(epsilon, pmf);
   }

   public static int getMaxSerializedSizeBytes(int k, long n, SketchType sketchType, boolean updatableMemFormat) {
      if (sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
         throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
      } else {
         KllHelper.GrowthStats gStats = KllHelper.getGrowthSchemeForGivenN(k, 8, n, sketchType, false);
         return updatableMemFormat ? gStats.updatableBytes : gStats.compactBytes;
      }
   }

   abstract String getMaxItemAsString();

   abstract String getMinItemAsString();

   public static double getNormalizedRankError(int k, boolean pmf) {
      return KllHelper.getNormalizedRankError(k, pmf);
   }

   public final double getNormalizedRankError(boolean pmf) {
      return getNormalizedRankError(this.getMinK(), pmf);
   }

   public final int getNumRetained() {
      return this.levelsArr[this.getNumLevels()] - this.levelsArr[0];
   }

   public int getSerializedSizeBytes() {
      return this.currentSerializedSizeBytes(false);
   }

   public boolean hasMemory() {
      WritableMemory wmem = this.getWritableMemory();
      return wmem != null;
   }

   public boolean isCompactMemoryFormat() {
      return this.hasMemory() && this.sketchStructure != KllSketch.SketchStructure.UPDATABLE;
   }

   public boolean isDirect() {
      WritableMemory wmem = this.getWritableMemory();
      return wmem != null ? wmem.isDirect() : false;
   }

   public final boolean isEmpty() {
      return this.getN() == 0L;
   }

   public final boolean isEstimationMode() {
      return this.getNumLevels() > 1;
   }

   public final boolean isMemoryUpdatableFormat() {
      return this.hasMemory() && this.sketchStructure == KllSketch.SketchStructure.UPDATABLE;
   }

   public final boolean isReadOnly() {
      return this.readOnly;
   }

   public final boolean isSameResource(Memory that) {
      WritableMemory wmem = this.getWritableMemory();
      return wmem != null && wmem.isSameResource(that);
   }

   public abstract void merge(KllSketch var1);

   public final String toString() {
      return this.toString(false, false);
   }

   public abstract String toString(boolean var1, boolean var2);

   final int currentSerializedSizeBytes(boolean updatable) {
      boolean myUpdatable = this.sketchType == KllSketch.SketchType.ITEMS_SKETCH ? false : updatable;
      long srcN = this.getN();
      SketchStructure tgtStructure;
      if (myUpdatable) {
         tgtStructure = KllSketch.SketchStructure.UPDATABLE;
      } else if (srcN == 0L) {
         tgtStructure = KllSketch.SketchStructure.COMPACT_EMPTY;
      } else if (srcN == 1L) {
         tgtStructure = KllSketch.SketchStructure.COMPACT_SINGLE;
      } else {
         tgtStructure = KllSketch.SketchStructure.COMPACT_FULL;
      }

      int totalBytes;
      if (tgtStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         totalBytes = 8;
      } else if (tgtStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         totalBytes = 8 + this.getSingleItemSizeBytes();
      } else if (tgtStructure == KllSketch.SketchStructure.COMPACT_FULL) {
         totalBytes = 20 + this.getLevelsArrSizeBytes(tgtStructure) + this.getMinMaxSizeBytes() + this.getRetainedItemsSizeBytes();
      } else {
         totalBytes = 20 + this.getLevelsArrSizeBytes(tgtStructure) + this.getMinMaxSizeBytes() + this.getTotalItemsNumBytes();
      }

      return totalBytes;
   }

   int[] getLevelsArray(SketchStructure structure) {
      if (structure == KllSketch.SketchStructure.UPDATABLE) {
         return (int[])this.levelsArr.clone();
      } else {
         return structure == KllSketch.SketchStructure.COMPACT_FULL ? Arrays.copyOf(this.levelsArr, this.levelsArr.length - 1) : new int[0];
      }
   }

   final int getLevelsArrSizeBytes(SketchStructure structure) {
      if (structure == KllSketch.SketchStructure.UPDATABLE) {
         return this.levelsArr.length * 4;
      } else {
         return structure == KllSketch.SketchStructure.COMPACT_FULL ? (this.levelsArr.length - 1) * 4 : 0;
      }
   }

   abstract int getM();

   abstract MemoryRequestServer getMemoryRequestServer();

   abstract int getMinK();

   abstract byte[] getMinMaxByteArr();

   abstract int getMinMaxSizeBytes();

   final int getNumLevels() {
      return this.sketchStructure != KllSketch.SketchStructure.UPDATABLE && this.sketchStructure != KllSketch.SketchStructure.COMPACT_FULL ? 1 : this.levelsArr.length - 1;
   }

   abstract byte[] getRetainedItemsByteArr();

   abstract int getRetainedItemsSizeBytes();

   abstract ArrayOfItemsSerDe getSerDe();

   abstract byte[] getSingleItemByteArr();

   abstract int getSingleItemSizeBytes();

   abstract byte[] getTotalItemsByteArr();

   abstract int getTotalItemsNumBytes();

   abstract WritableMemory getWritableMemory();

   abstract void incN(int var1);

   abstract void incNumLevels();

   final boolean isCompactSingleItem() {
      return this.hasMemory() && this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE && this.getN() == 1L;
   }

   boolean isDoublesSketch() {
      return this.sketchType == KllSketch.SketchType.DOUBLES_SKETCH;
   }

   boolean isFloatsSketch() {
      return this.sketchType == KllSketch.SketchType.FLOATS_SKETCH;
   }

   boolean isLongsSketch() {
      return this.sketchType == KllSketch.SketchType.LONGS_SKETCH;
   }

   boolean isItemsSketch() {
      return this.sketchType == KllSketch.SketchType.ITEMS_SKETCH;
   }

   abstract boolean isLevelZeroSorted();

   boolean isSingleItem() {
      return this.getN() == 1L;
   }

   final void setLevelsArray(int[] levelsArr) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         this.levelsArr = levelsArr;
         WritableMemory wmem = this.getWritableMemory();
         if (wmem != null) {
            wmem.putIntArray(20L, this.levelsArr, 0, levelsArr.length);
         }

      }
   }

   final void setLevelsArrayAt(int index, int idxVal) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         this.levelsArr[index] = idxVal;
         WritableMemory wmem = this.getWritableMemory();
         if (wmem != null) {
            int offset = 20 + index * 4;
            wmem.putInt((long)offset, idxVal);
         }

      }
   }

   abstract void setLevelZeroSorted(boolean var1);

   abstract void setMinK(int var1);

   abstract void setN(long var1);

   abstract void setNumLevels(int var1);

   abstract void setWritableMemory(WritableMemory var1);

   public static enum SketchType {
      DOUBLES_SKETCH(8, "KllDoublesSketch"),
      FLOATS_SKETCH(4, "KllFloatsSketch"),
      ITEMS_SKETCH(0, "KllItemsSketch"),
      LONGS_SKETCH(8, "KllLongsSketch");

      private int typeBytes;
      private String name;

      private SketchType(int typeBytes, String name) {
         this.typeBytes = typeBytes;
         this.name = name;
      }

      public int getBytes() {
         return this.typeBytes;
      }

      public String getName() {
         return this.name;
      }
   }

   public static enum SketchStructure {
      COMPACT_EMPTY(2, 1),
      COMPACT_SINGLE(2, 2),
      COMPACT_FULL(5, 1),
      UPDATABLE(5, 3);

      private int preInts;
      private int serVer;

      private SketchStructure(int preInts, int serVer) {
         this.preInts = preInts;
         this.serVer = serVer;
      }

      public int getPreInts() {
         return this.preInts;
      }

      public int getSerVer() {
         return this.serVer;
      }

      public static SketchStructure getSketchStructure(int preInts, int serVer) {
         SketchStructure[] ssArr = values();

         for(int i = 0; i < ssArr.length; ++i) {
            if (ssArr[i].preInts == preInts && ssArr[i].serVer == serVer) {
               return ssArr[i];
            }
         }

         throw new SketchesArgumentException("Error combination of PreInts and SerVer: PreInts: " + preInts + ", SerVer: " + serVer);
      }
   }
}
