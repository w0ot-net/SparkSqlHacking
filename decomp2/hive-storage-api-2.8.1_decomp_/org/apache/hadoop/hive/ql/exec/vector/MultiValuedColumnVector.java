package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;

public abstract class MultiValuedColumnVector extends ColumnVector {
   public long[] offsets;
   public long[] lengths;
   public int childCount = 0;

   public MultiValuedColumnVector(ColumnVector.Type type, int len) {
      super(type, len);
      this.offsets = new long[len];
      this.lengths = new long[len];
   }

   protected abstract void childFlatten(boolean var1, int[] var2, int var3);

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            if (selectedInUse) {
               for(int i = 0; i < size; ++i) {
                  this.isNull[sel[i]] = true;
               }
            } else {
               Arrays.fill(this.isNull, 0, size, true);
            }
         } else {
            if (selectedInUse) {
               for(int i = 0; i < size; ++i) {
                  int row = sel[i];
                  this.offsets[row] = this.offsets[0];
                  this.lengths[row] = this.lengths[0];
                  this.isNull[row] = false;
               }
            } else {
               Arrays.fill(this.offsets, 0, size, this.offsets[0]);
               Arrays.fill(this.lengths, 0, size, this.lengths[0]);
               Arrays.fill(this.isNull, 0, size, false);
            }

            if (this.offsets[0] != 0L) {
               throw new IllegalArgumentException("Repeating offset isn't 0, but " + this.offsets[0]);
            }

            this.childFlatten(false, (int[])null, (int)this.lengths[0]);
         }

         this.isRepeating = false;
         this.noNulls = false;
      } else {
         if (!selectedInUse) {
            this.childFlatten(false, (int[])null, this.childCount);
         } else {
            int childSize = 0;

            for(int i = 0; i < size; ++i) {
               childSize = (int)((long)childSize + this.lengths[sel[i]]);
            }

            int[] childSelection = new int[childSize];
            int idx = 0;

            for(int i = 0; i < size; ++i) {
               int row = sel[i];

               for(int elem = 0; (long)elem < this.lengths[row]; ++elem) {
                  childSelection[idx++] = (int)(this.offsets[row] + (long)elem);
               }
            }

            this.childFlatten(true, childSelection, childSize);
         }

         this.flattenNoNulls(selectedInUse, sel, size);
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.offsets.length) {
         long[] oldOffsets = this.offsets;
         this.offsets = new long[size];
         long[] oldLengths = this.lengths;
         this.lengths = new long[size];
         if (preserveData) {
            if (this.isRepeating) {
               this.offsets[0] = oldOffsets[0];
               this.lengths[0] = oldLengths[0];
            } else {
               System.arraycopy(oldOffsets, 0, this.offsets, 0, oldOffsets.length);
               System.arraycopy(oldLengths, 0, this.lengths, 0, oldLengths.length);
            }
         }
      }

   }

   public void init() {
      super.init();
      this.childCount = 0;
   }

   public void reset() {
      super.reset();
      this.childCount = 0;
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      MultiValuedColumnVector other = (MultiValuedColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.offsets = this.offsets;
      other.lengths = this.lengths;
      other.childCount = this.childCount;
   }
}
