package org.apache.hadoop.hive.ql.exec.vector;

public class StructColumnVector extends ColumnVector {
   public ColumnVector[] fields;

   public StructColumnVector() {
      this(1024);
   }

   public StructColumnVector(int len, ColumnVector... fields) {
      super(ColumnVector.Type.STRUCT, len);
      this.fields = fields;
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].flatten(selectedInUse, sel, size);
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
               ColumnVector[] inputFields = ((StructColumnVector)inputColVector).fields;

               for(int i = 0; i < inputFields.length; ++i) {
                  ColumnVector inputField = inputFields[i];
                  ColumnVector outputField = this.fields[i];
                  outputField.isNull[outputElementNum] = false;
                  outputField.setElement(outputElementNum, inputElementNum, inputField);
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

         for(int i = 0; i < this.fields.length; ++i) {
            if (i != 0) {
               buffer.append(", ");
            }

            this.fields[i].stringifyValue(buffer, row);
         }

         buffer.append(']');
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].ensureSize(size, preserveData);
      }

   }

   public void reset() {
      super.reset();

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].reset();
      }

   }

   public void init() {
      super.init();

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].init();
      }

   }

   public void unFlatten() {
      super.unFlatten();

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].unFlatten();
      }

   }

   public void setRepeating(boolean isRepeating) {
      super.setRepeating(isRepeating);

      for(int i = 0; i < this.fields.length; ++i) {
         this.fields[i].setRepeating(isRepeating);
      }

   }

   public void shallowCopyTo(ColumnVector otherCv) {
      throw new UnsupportedOperationException();
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      throw new RuntimeException("Not supported");
   }
}
