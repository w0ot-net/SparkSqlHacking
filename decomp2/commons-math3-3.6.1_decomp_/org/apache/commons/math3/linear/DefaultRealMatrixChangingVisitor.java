package org.apache.commons.math3.linear;

public class DefaultRealMatrixChangingVisitor implements RealMatrixChangingVisitor {
   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
   }

   public double visit(int row, int column, double value) {
      return value;
   }

   public double end() {
      return (double)0.0F;
   }
}
