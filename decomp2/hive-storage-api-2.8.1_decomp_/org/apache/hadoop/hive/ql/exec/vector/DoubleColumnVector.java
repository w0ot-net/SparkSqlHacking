package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;

public class DoubleColumnVector extends ColumnVector {
   public double[] vector;
   public static final double NULL_VALUE = Double.NaN;

   public DoubleColumnVector() {
      this(1024);
   }

   public DoubleColumnVector(int len) {
      super(ColumnVector.Type.DOUBLE, len);
      this.vector = new double[len];
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      DoubleColumnVector output = (DoubleColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            output.vector[0] = this.vector[0];
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!outputColVector.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     output.vector[i] = this.vector[i];
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.vector[i] = this.vector[i];
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               System.arraycopy(this.vector, 0, output.vector, 0, size);
            }
         } else {
            output.noNulls = false;
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  output.isNull[i] = this.isNull[i];
                  output.vector[i] = this.vector[i];
               }
            } else {
               System.arraycopy(this.isNull, 0, output.isNull, 0, size);

               for(int i = 0; i < size; ++i) {
                  output.vector[i] = this.vector[i];
               }
            }
         }

      }
   }

   public void fill(double value) {
      this.isRepeating = true;
      this.isNull[0] = false;
      this.vector[0] = value;
   }

   public void fillWithNulls() {
      this.noNulls = false;
      this.isRepeating = true;
      this.vector[0] = Double.NaN;
      this.isNull[0] = true;
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         double repeatVal = this.vector[0];
         if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               this.vector[i] = repeatVal;
            }
         } else {
            Arrays.fill(this.vector, 0, size, repeatVal);
         }

         this.flattenRepeatingNulls(selectedInUse, sel, size);
      }

      this.flattenNoNulls(selectedInUse, sel, size);
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
               this.vector[outputElementNum] = ((DoubleColumnVector)inputColVector).vector[inputElementNum];
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
         buffer.append(this.vector[row]);
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.vector.length) {
         double[] oldArray = this.vector;
         this.vector = new double[size];
         if (preserveData) {
            if (this.isRepeating) {
               this.vector[0] = oldArray[0];
            } else {
               System.arraycopy(oldArray, 0, this.vector, 0, oldArray.length);
            }
         }
      }

   }

   public void shallowCopyTo(ColumnVector otherCv) {
      DoubleColumnVector other = (DoubleColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.vector = this.vector;
   }
}
