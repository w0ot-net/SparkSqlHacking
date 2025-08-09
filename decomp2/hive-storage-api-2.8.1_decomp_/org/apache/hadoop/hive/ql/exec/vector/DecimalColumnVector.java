package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public class DecimalColumnVector extends ColumnVector {
   public HiveDecimalWritable[] vector;
   public short scale;
   public short precision;

   public DecimalColumnVector(int precision, int scale) {
      this(1024, precision, scale);
   }

   public DecimalColumnVector(int size, int precision, int scale) {
      super(ColumnVector.Type.DECIMAL, size);
      this.precision = (short)precision;
      this.scale = (short)scale;
      this.vector = new HiveDecimalWritable[size];

      for(int i = 0; i < size; ++i) {
         this.vector[i] = new HiveDecimalWritable(0L);
      }

   }

   public void fill(HiveDecimal value) {
      this.isRepeating = true;
      this.isNull[0] = false;
      if (this.vector[0] == null) {
         this.vector[0] = new HiveDecimalWritable(value);
      }

      this.set(0, (HiveDecimal)value);
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         HiveDecimalWritable repeat = this.vector[0];
         if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               this.vector[i].set(repeat);
            }
         } else {
            for(int i = 0; i < size; ++i) {
               this.vector[i].set(repeat);
            }
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
               this.vector[outputElementNum].set((HiveDecimalWritable)((DecimalColumnVector)inputColVector).vector[inputElementNum], this.precision, this.scale);
               if (!this.vector[outputElementNum].isSet()) {
                  this.isNull[outputElementNum] = true;
                  this.noNulls = false;
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
         buffer.append(this.vector[row].toString());
      }

   }

   public void set(int elementNum, HiveDecimalWritable writable) {
      this.vector[elementNum].set((HiveDecimalWritable)writable, this.precision, this.scale);
      if (!this.vector[elementNum].isSet()) {
         this.noNulls = false;
         this.isNull[elementNum] = true;
      }

   }

   public void set(int elementNum, HiveDecimal hiveDec) {
      this.vector[elementNum].set((HiveDecimal)hiveDec, this.precision, this.scale);
      if (!this.vector[elementNum].isSet()) {
         this.noNulls = false;
         this.isNull[elementNum] = true;
      }

   }

   public void setNullDataValue(int elementNum) {
      this.vector[elementNum].setFromLongAndScale(1L, this.scale);
   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.vector.length) {
         HiveDecimalWritable[] oldArray = this.vector;
         this.vector = new HiveDecimalWritable[size];
         int initPos = 0;
         if (preserveData) {
            initPos = oldArray.length;
            System.arraycopy(oldArray, 0, this.vector, 0, oldArray.length);
         }

         for(int i = initPos; i < this.vector.length; ++i) {
            this.vector[i] = new HiveDecimalWritable(0L);
         }

      }
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      DecimalColumnVector other = (DecimalColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.scale = this.scale;
      other.precision = this.precision;
      other.vector = this.vector;
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      DecimalColumnVector output = (DecimalColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
            output.vector[0].setFromLong(0L);
         } else {
            outputIsNull[0] = false;
            output.set(0, (HiveDecimalWritable)this.vector[0]);
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!outputColVector.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     output.set(i, this.vector[i]);
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.set(i, this.vector[i]);
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               for(int i = 0; i != size; ++i) {
                  output.set(i, this.vector[i]);
               }
            }
         } else if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               if (!this.isNull[i]) {
                  output.isNull[i] = false;
                  output.set(i, this.vector[i]);
               } else {
                  output.isNull[i] = true;
                  output.noNulls = false;
               }
            }
         } else {
            for(int i = 0; i < size; ++i) {
               if (!this.isNull[i]) {
                  output.isNull[i] = false;
                  output.set(i, this.vector[i]);
               } else {
                  output.isNull[i] = true;
                  output.noNulls = false;
               }
            }
         }

      }
   }
}
