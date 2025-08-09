package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;

public class LongColumnVector extends ColumnVector {
   public long[] vector;
   public static final long NULL_VALUE = 1L;

   public LongColumnVector() {
      this(1024);
   }

   public LongColumnVector(int len) {
      super(ColumnVector.Type.LONG, len);
      this.vector = new long[len];
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      LongColumnVector output = (LongColumnVector)outputColVector;
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
               System.arraycopy(this.vector, 0, output.vector, 0, size);
            }
         }

      }
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, DoubleColumnVector output) {
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            output.vector[0] = (double)this.vector[0];
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!output.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     output.vector[i] = (double)this.vector[i];
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.vector[i] = (double)this.vector[i];
                  }
               }
            } else {
               if (!output.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  output.noNulls = true;
               }

               System.arraycopy(this.vector, 0, output.vector, 0, size);
            }
         } else {
            output.noNulls = false;
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  output.isNull[i] = this.isNull[i];
                  output.vector[i] = (double)this.vector[i];
               }
            } else {
               System.arraycopy(this.isNull, 0, output.isNull, 0, size);
               System.arraycopy(this.vector, 0, output.vector, 0, size);
            }
         }

      }
   }

   public void fill(long value) {
      this.isRepeating = true;
      this.isNull[0] = false;
      this.vector[0] = value;
   }

   public LongColumnVector fillWithNulls() {
      this.noNulls = false;
      this.isRepeating = true;
      this.vector[0] = 1L;
      this.isNull[0] = true;
      return this;
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         long repeatVal = this.vector[0];
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
               this.vector[outputElementNum] = ((LongColumnVector)inputColVector).vector[inputElementNum];
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
         long[] oldArray = this.vector;
         this.vector = new long[size];
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
      LongColumnVector other = (LongColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.vector = this.vector;
   }
}
