package org.apache.hadoop.hive.ql.exec.vector;

public class VoidColumnVector extends ColumnVector {
   public VoidColumnVector() {
      this(1024);
   }

   public VoidColumnVector(int len) {
      super(ColumnVector.Type.VOID, len);
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      throw new UnsupportedOperationException();
   }

   public void setElement(int outputElementNum, int inputElementNum, ColumnVector inputColVector) {
      throw new UnsupportedOperationException();
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      throw new UnsupportedOperationException();
   }

   public void stringifyValue(StringBuilder buffer, int row) {
      throw new UnsupportedOperationException();
   }
}
