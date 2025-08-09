package org.apache.hadoop.hive.ql.exec.vector;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public class Decimal64ColumnVector extends LongColumnVector {
   public short scale;
   public short precision;
   private HiveDecimalWritable scratchHiveDecWritable;

   public Decimal64ColumnVector(int precision, int scale) {
      this(1024, precision, scale);
   }

   public Decimal64ColumnVector(int size, int precision, int scale) {
      super(size);
      this.precision = (short)precision;
      this.scale = (short)scale;
      this.scratchHiveDecWritable = new HiveDecimalWritable();
   }

   public void set(int elementNum, HiveDecimalWritable writable) {
      this.scratchHiveDecWritable.set(writable);
      this.scratchHiveDecWritable.mutateEnforcePrecisionScale(this.precision, this.scale);
      if (!this.scratchHiveDecWritable.isSet()) {
         this.noNulls = false;
         this.isNull[elementNum] = true;
      } else {
         this.vector[elementNum] = this.scratchHiveDecWritable.serialize64(this.scale);
      }

   }

   public void set(int elementNum, HiveDecimal hiveDec) {
      this.scratchHiveDecWritable.set(hiveDec);
      this.scratchHiveDecWritable.mutateEnforcePrecisionScale(this.precision, this.scale);
      if (!this.scratchHiveDecWritable.isSet()) {
         this.noNulls = false;
         this.isNull[elementNum] = true;
      } else {
         this.vector[elementNum] = this.scratchHiveDecWritable.serialize64(this.scale);
      }

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
               Decimal64ColumnVector decimal64ColVector = (Decimal64ColumnVector)inputColVector;
               this.scratchHiveDecWritable.deserialize64(decimal64ColVector.vector[inputElementNum], decimal64ColVector.scale);
               this.scratchHiveDecWritable.mutateEnforcePrecisionScale(this.precision, this.scale);
               if (this.scratchHiveDecWritable.isSet()) {
                  this.vector[inputElementNum] = this.scratchHiveDecWritable.serialize64(this.scale);
               } else {
                  this.noNulls = false;
                  this.isNull[inputElementNum] = true;
               }
            }

         }
      }
   }

   public HiveDecimalWritable getScratchWritable() {
      return this.scratchHiveDecWritable;
   }
}
