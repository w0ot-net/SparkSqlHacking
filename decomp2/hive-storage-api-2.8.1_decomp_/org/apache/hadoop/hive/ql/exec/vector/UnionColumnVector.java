package org.apache.hadoop.hive.ql.exec.vector;

public class UnionColumnVector extends ColumnVector {
   public int[] tags;
   public ColumnVector[] fields;

   public UnionColumnVector() {
      this(1024);
   }

   public UnionColumnVector(int len, ColumnVector... fields) {
      super(ColumnVector.Type.UNION, len);
      this.tags = new int[len];
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
               UnionColumnVector input = (UnionColumnVector)inputColVector;
               int tag = input.tags[inputElementNum];
               this.tags[outputElementNum] = tag;
               ColumnVector inputField = input.fields[tag];
               ColumnVector outputField = this.fields[tag];
               outputField.isNull[outputElementNum] = false;
               outputField.setElement(outputElementNum, inputElementNum, inputField);
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
         buffer.append("{\"tag\": ");
         buffer.append(this.tags[row]);
         buffer.append(", \"value\": ");
         this.fields[this.tags[row]].stringifyValue(buffer, row);
         buffer.append('}');
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (this.tags.length < size) {
         if (preserveData) {
            int[] oldTags = this.tags;
            this.tags = new int[size];
            System.arraycopy(oldTags, 0, this.tags, 0, oldTags.length);
         } else {
            this.tags = new int[size];
         }

         for(int i = 0; i < this.fields.length; ++i) {
            this.fields[i].ensureSize(size, preserveData);
         }
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
