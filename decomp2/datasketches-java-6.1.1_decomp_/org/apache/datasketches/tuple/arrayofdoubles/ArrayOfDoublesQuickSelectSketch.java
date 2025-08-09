package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.QuickSelect;

abstract class ArrayOfDoublesQuickSelectSketch extends ArrayOfDoublesUpdatableSketch {
   static final byte serialVersionUID = 1;
   static final int LG_NOM_ENTRIES_BYTE = 16;
   static final int LG_CUR_CAPACITY_BYTE = 17;
   static final int LG_RESIZE_FACTOR_BYTE = 18;
   static final int SAMPLING_P_FLOAT = 20;
   static final int RETAINED_ENTRIES_INT = 24;
   static final int ENTRIES_START = 32;
   static final int DEFAULT_LG_RESIZE_FACTOR = 3;
   int rebuildThreshold_;
   int lgCurrentCapacity_;

   ArrayOfDoublesQuickSelectSketch(int numValues, long seed) {
      super(numValues, seed);
   }

   abstract void updateValues(int var1, double[] var2);

   abstract void setNotEmpty();

   abstract boolean isInSamplingMode();

   abstract void rebuild(int var1);

   abstract long getKey(int var1);

   abstract void setValues(int var1, double[] var2);

   abstract void incrementCount();

   abstract void setThetaLong(long var1);

   abstract int insertKey(long var1);

   abstract int findOrInsertKey(long var1);

   abstract double[] find(long var1);

   abstract int getSerializedSizeBytes();

   abstract void serializeInto(WritableMemory var1);

   public void trim() {
      if (this.getRetainedEntries() > this.getNominalEntries()) {
         this.setThetaLong(this.getNewThetaLong());
         this.rebuild();
      }

   }

   public int getMaxBytes() {
      int nomEntries = this.getNominalEntries();
      int numValues = this.getNumValues();
      return getMaxBytes(nomEntries, numValues);
   }

   public int getCurrentBytes() {
      return this.getSerializedSizeBytes();
   }

   static int getMaxBytes(int nomEntries, int numValues) {
      return 32 + (8 + 8 * numValues) * Util.ceilingPowerOf2(nomEntries) * 2;
   }

   void merge(long key, double[] values) {
      this.setNotEmpty();
      if (key < this.thetaLong_) {
         int index = this.findOrInsertKey(key);
         if (index < 0) {
            this.incrementCount();
            this.setValues(~index, values);
         } else {
            this.updateValues(index, values);
         }

         this.rebuildIfNeeded();
      }

   }

   void rebuildIfNeeded() {
      if (this.getRetainedEntries() > this.rebuildThreshold_) {
         if (this.getCurrentCapacity() > this.getNominalEntries()) {
            this.setThetaLong(this.getNewThetaLong());
            this.rebuild();
         } else {
            this.rebuild(this.getCurrentCapacity() * this.getResizeFactor().getValue());
         }

      }
   }

   void rebuild() {
      this.rebuild(this.getCurrentCapacity());
   }

   void insert(long key, double[] values) {
      int index = this.insertKey(key);
      this.setValues(index, values);
      this.incrementCount();
   }

   final void setRebuildThreshold() {
      if (this.getCurrentCapacity() > this.getNominalEntries()) {
         this.rebuildThreshold_ = (int)((double)this.getCurrentCapacity() * (double)0.9375F);
      } else {
         this.rebuildThreshold_ = (int)((double)this.getCurrentCapacity() * (double)0.5F);
      }

   }

   void insertOrIgnore(long key, double[] values) {
      if (values.length != this.getNumValues()) {
         throw new SketchesArgumentException("input array of values must have " + this.getNumValues() + " elements, but has " + values.length);
      } else {
         this.setNotEmpty();
         if (key != 0L && key < this.thetaLong_) {
            int index = this.findOrInsertKey(key);
            if (index < 0) {
               this.incrementCount();
               this.setValues(~index, values);
            } else {
               this.updateValues(index, values);
            }

            this.rebuildIfNeeded();
         }
      }
   }

   long getNewThetaLong() {
      long[] keys = new long[this.getRetainedEntries()];
      int i = 0;

      for(int j = 0; j < this.getCurrentCapacity(); ++j) {
         long key = this.getKey(j);
         if (key != 0L) {
            keys[i++] = key;
         }
      }

      return QuickSelect.select((long[])keys, 0, this.getRetainedEntries() - 1, this.getNominalEntries());
   }
}
