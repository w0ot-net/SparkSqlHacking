package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;

abstract class DoublesSketchAccessor extends DoublesBufferAccessor {
   static final int BB_LVL_IDX = -1;
   final DoublesSketch ds_;
   final boolean forceSize_;
   long n_;
   int currLvl_;
   int numItems_;
   int offset_;

   DoublesSketchAccessor(DoublesSketch ds, boolean forceSize, int level) {
      this(checkLvl(level), ds, forceSize, level);
   }

   private DoublesSketchAccessor(boolean secure, DoublesSketch ds, boolean forceSize, int level) {
      this.ds_ = ds;
      this.forceSize_ = forceSize;
      this.setLevel(level);
   }

   private static final boolean checkLvl(int level) {
      if (level != -1 && level < 0) {
         throw new SketchesArgumentException("Parameter level is < 0.");
      } else {
         return true;
      }
   }

   static DoublesSketchAccessor wrap(DoublesSketch ds) {
      return wrap(ds, false);
   }

   static DoublesSketchAccessor wrap(DoublesSketch ds, boolean forceSize) {
      return (DoublesSketchAccessor)(ds.hasMemory() ? new DirectDoublesSketchAccessor(ds, forceSize, -1) : new HeapDoublesSketchAccessor(ds, forceSize, -1));
   }

   abstract DoublesSketchAccessor copyAndSetLevel(int var1);

   DoublesSketchAccessor setLevel(int lvl) {
      this.currLvl_ = lvl;
      if (lvl == -1) {
         this.numItems_ = this.forceSize_ ? this.ds_.getK() * 2 : this.ds_.getBaseBufferCount();
         this.offset_ = this.ds_.hasMemory() ? 32 : 0;
      } else {
         if ((this.ds_.getBitPattern() & 1L << lvl) <= 0L && !this.forceSize_) {
            this.numItems_ = 0;
         } else {
            this.numItems_ = this.ds_.getK();
         }

         int levelStart;
         if (this.ds_.isCompact()) {
            levelStart = this.ds_.getBaseBufferCount() + this.countValidLevelsBelow(lvl) * this.ds_.getK();
         } else {
            levelStart = (2 + this.currLvl_) * this.ds_.getK();
         }

         if (this.ds_.hasMemory()) {
            int preLongsAndExtra = Family.QUANTILES.getMaxPreLongs() + 2;
            this.offset_ = preLongsAndExtra + levelStart << 3;
         } else {
            this.offset_ = levelStart;
         }
      }

      this.n_ = this.ds_.getN();
      return this;
   }

   int numItems() {
      return this.numItems_;
   }

   abstract double get(int var1);

   abstract double[] getArray(int var1, int var2);

   abstract double set(int var1, double var2);

   abstract void putArray(double[] var1, int var2, int var3, int var4);

   abstract void sort();

   private int countValidLevelsBelow(int tgtLvl) {
      int count = 0;
      long bitPattern = this.ds_.getBitPattern();

      for(int i = 0; i < tgtLvl && bitPattern > 0L; bitPattern >>>= 1) {
         if ((bitPattern & 1L) > 0L) {
            ++count;
         }

         ++i;
      }

      return count;
   }
}
