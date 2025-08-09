package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;

public class MapColumnVector extends MultiValuedColumnVector {
   public ColumnVector keys;
   public ColumnVector values;

   public MapColumnVector() {
      this(1024, (ColumnVector)null, (ColumnVector)null);
   }

   public MapColumnVector(int len, ColumnVector keys, ColumnVector values) {
      super(ColumnVector.Type.MAP, len);
      this.keys = keys;
      this.values = values;
   }

   protected void childFlatten(boolean useSelected, int[] selected, int size) {
      this.keys.flatten(useSelected, selected, size);
      this.values.flatten(useSelected, selected, size);
   }

   public void setElement(int outputElementNum, int inputElementNum, ColumnVector inputColVector) {
      if (this.isRepeating && outputElementNum != 0) {
         throw new RuntimeException("Output column number expected to be 0 when isRepeating");
      } else {
         if (inputColVector.isRepeating) {
            inputElementNum = 0;
         }

         if (this.noNulls || !this.isNull[outputElementNum]) {
            if (!inputColVector.noNulls && inputColVector.isNull[inputElementNum]) {
               this.isNull[outputElementNum] = true;
               this.noNulls = false;
            } else {
               MapColumnVector input = (MapColumnVector)inputColVector;
               this.isNull[outputElementNum] = false;
               int offset = this.childCount;
               int length = (int)input.lengths[inputElementNum];
               int inputOffset = (int)input.offsets[inputElementNum];
               this.offsets[outputElementNum] = (long)offset;
               this.childCount += length;
               this.lengths[outputElementNum] = (long)length;
               this.keys.ensureSize(this.childCount, true);
               this.values.ensureSize(this.childCount, true);

               for(int i = 0; i < length; ++i) {
                  int inputIndex = inputOffset + i;
                  int outputIndex = i + offset;
                  this.keys.isNull[outputIndex] = false;
                  this.keys.setElement(outputIndex, inputIndex, input.keys);
                  this.values.isNull[outputIndex] = false;
                  this.values.setElement(outputIndex, inputIndex, input.values);
               }
            }

         }
      }
   }

   public void stringifyValue(StringBuilder buffer, int row) {
      if (this.isRepeating) {
         row = 0;
      }

      if (!this.noNulls && this.isNull[row]) {
         buffer.append("null");
      } else {
         buffer.append('[');
         boolean isFirst = true;

         for(long i = this.offsets[row]; i < this.offsets[row] + this.lengths[row]; ++i) {
            if (isFirst) {
               isFirst = false;
            } else {
               buffer.append(", ");
            }

            buffer.append("{\"key\": ");
            this.keys.stringifyValue(buffer, (int)i);
            buffer.append(", \"value\": ");
            this.values.stringifyValue(buffer, (int)i);
            buffer.append('}');
         }

         buffer.append(']');
      }

   }

   public void init() {
      super.init();
      this.keys.init();
      this.values.init();
   }

   public void reset() {
      super.reset();
      this.keys.reset();
      this.values.reset();
   }

   public void unFlatten() {
      super.unFlatten();
      if (!this.isRepeating || this.noNulls || !this.isNull[0]) {
         this.keys.unFlatten();
         this.values.unFlatten();
      }

   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      MapColumnVector output = (MapColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            outputColVector.setElement(0, 0, this);
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!outputColVector.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     outputColVector.setElement(i, i, this);
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputColVector.setElement(i, i, this);
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               this.keys.shallowCopyTo(output.keys);
               this.values.shallowCopyTo(output.values);
               System.arraycopy(this.offsets, 0, output.offsets, 0, size);
               System.arraycopy(this.lengths, 0, output.lengths, 0, size);
               output.childCount = this.childCount;
            }
         } else {
            output.noNulls = false;
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  output.isNull[i] = this.isNull[i];
                  outputColVector.setElement(i, i, this);
               }
            } else {
               this.keys.shallowCopyTo(output.keys);
               this.values.shallowCopyTo(output.values);
               System.arraycopy(this.isNull, 0, output.isNull, 0, size);
               System.arraycopy(this.offsets, 0, output.offsets, 0, size);
               System.arraycopy(this.lengths, 0, output.lengths, 0, size);
               output.childCount = this.childCount;
            }
         }

      }
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      MapColumnVector other = (MapColumnVector)otherCv;
      super.shallowCopyTo(other);
      this.keys.shallowCopyTo(other.keys);
      this.values.shallowCopyTo(other.values);
   }
}
