package org.apache.datasketches.kll;

import org.apache.datasketches.common.ArrayOfBooleansSerDe;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;

final class KllMemoryValidate {
   final Memory srcMem;
   final ArrayOfItemsSerDe serDe;
   final KllSketch.SketchType sketchType;
   final KllSketch.SketchStructure sketchStructure;
   final int preInts;
   final int serVer;
   final int familyID;
   final int flags;
   final int k;
   final int m;
   final boolean emptyFlag;
   final boolean level0SortedFlag;
   long n;
   int minK;
   int numLevels;
   int[] levelsArr;
   int sketchBytes;
   private int typeBytes;
   static final String EMPTY_FLAG_AND_COMPACT_EMPTY = "A compact empty sketch should have empty flag set. ";
   static final String EMPTY_FLAG_AND_COMPACT_FULL = "A compact full sketch should not have empty flag set. ";
   static final String EMPTY_FLAG_AND_COMPACT_SINGLE = "A single item sketch should not have empty flag set. ";
   static final String SRC_NOT_KLL;
   static final String MEMORY_TOO_SMALL = "A sketch memory image must be at least 8 bytes. ";

   KllMemoryValidate(Memory srcMem, KllSketch.SketchType sketchType) {
      this(srcMem, sketchType, (ArrayOfItemsSerDe)null);
   }

   KllMemoryValidate(Memory srcMem, KllSketch.SketchType sketchType, ArrayOfItemsSerDe serDe) {
      this.sketchBytes = 0;
      this.typeBytes = 0;
      long memCapBytes = srcMem.getCapacity();
      if (memCapBytes < 8L) {
         throw new SketchesArgumentException("A sketch memory image must be at least 8 bytes. " + memCapBytes);
      } else {
         this.srcMem = srcMem;
         this.sketchType = sketchType;
         this.serDe = serDe;
         this.preInts = KllPreambleUtil.getMemoryPreInts(srcMem);
         this.serVer = KllPreambleUtil.getMemorySerVer(srcMem);
         this.sketchStructure = KllSketch.SketchStructure.getSketchStructure(this.preInts, this.serVer);
         this.familyID = KllPreambleUtil.getMemoryFamilyID(srcMem);
         if (this.familyID != Family.KLL.getID()) {
            throw new SketchesArgumentException(SRC_NOT_KLL + this.familyID);
         } else {
            this.flags = KllPreambleUtil.getMemoryFlags(srcMem);
            this.k = KllPreambleUtil.getMemoryK(srcMem);
            this.m = KllPreambleUtil.getMemoryM(srcMem);
            KllHelper.checkM(this.m);
            KllHelper.checkK(this.k, this.m);
            this.emptyFlag = KllPreambleUtil.getMemoryEmptyFlag(srcMem);
            this.level0SortedFlag = KllPreambleUtil.getMemoryLevelZeroSortedFlag(srcMem);
            if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
               this.typeBytes = 8;
            } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
               this.typeBytes = 4;
            } else {
               this.typeBytes = 0;
            }

            this.validate();
         }
      }
   }

   private void validate() {
      switch (this.sketchStructure) {
         case COMPACT_FULL:
            if (this.emptyFlag) {
               throw new SketchesArgumentException("A compact full sketch should not have empty flag set. ");
            }

            this.n = KllPreambleUtil.getMemoryN(this.srcMem);
            this.minK = KllPreambleUtil.getMemoryMinK(this.srcMem);
            this.numLevels = KllPreambleUtil.getMemoryNumLevels(this.srcMem);
            this.levelsArr = new int[this.numLevels + 1];
            this.srcMem.getIntArray(20L, this.levelsArr, 0, this.numLevels);
            int capacityItems = KllHelper.computeTotalItemCapacity(this.k, this.m, this.numLevels);
            this.levelsArr[this.numLevels] = capacityItems;
            this.sketchBytes = computeSketchBytes(this.srcMem, this.sketchType, this.levelsArr, false, this.serDe);
            break;
         case COMPACT_EMPTY:
            if (!this.emptyFlag) {
               throw new SketchesArgumentException("A compact empty sketch should have empty flag set. ");
            }

            this.n = 0L;
            this.minK = this.k;
            this.numLevels = 1;
            this.levelsArr = new int[]{this.k, this.k};
            this.sketchBytes = 8;
            break;
         case COMPACT_SINGLE:
            if (this.emptyFlag) {
               throw new SketchesArgumentException("A single item sketch should not have empty flag set. ");
            }

            this.n = 1L;
            this.minK = this.k;
            this.numLevels = 1;
            this.levelsArr = new int[]{this.k - 1, this.k};
            if (this.sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
               this.sketchBytes = 8 + this.serDe.sizeOf(this.srcMem, 8L, 1);
            } else {
               this.sketchBytes = 8 + this.typeBytes;
            }
            break;
         case UPDATABLE:
            this.n = KllPreambleUtil.getMemoryN(this.srcMem);
            this.minK = KllPreambleUtil.getMemoryMinK(this.srcMem);
            this.numLevels = KllPreambleUtil.getMemoryNumLevels(this.srcMem);
            this.levelsArr = new int[this.numLevels + 1];
            this.srcMem.getIntArray(20L, this.levelsArr, 0, this.numLevels + 1);
            this.sketchBytes = computeSketchBytes(this.srcMem, this.sketchType, this.levelsArr, true, this.serDe);
      }

   }

   static int computeSketchBytes(Memory srcMem, KllSketch.SketchType sketchType, int[] levelsArr, boolean updatable, ArrayOfItemsSerDe serDe) {
      int numLevels = levelsArr.length - 1;
      int capacityItems = levelsArr[numLevels];
      int retainedItems = levelsArr[numLevels] - levelsArr[0];
      int levelsLen = updatable ? levelsArr.length : levelsArr.length - 1;
      int numItems = updatable ? capacityItems : retainedItems;
      int offsetBytes = 20 + levelsLen * 4;
      if (sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
         if (serDe instanceof ArrayOfBooleansSerDe) {
            offsetBytes += serDe.sizeOf(srcMem, (long)offsetBytes, numItems) + 2;
         } else {
            offsetBytes += serDe.sizeOf(srcMem, (long)offsetBytes, numItems + 2);
         }
      } else {
         int typeBytes = sketchType.getBytes();
         offsetBytes += (numItems + 2) * typeBytes;
      }

      return offsetBytes;
   }

   static {
      SRC_NOT_KLL = "FamilyID Field must be: " + Family.KLL.getID() + ", NOT: ";
   }
}
