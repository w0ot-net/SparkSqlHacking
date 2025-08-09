package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public class DefaultFieldMatrixChangingVisitor implements FieldMatrixChangingVisitor {
   private final FieldElement zero;

   public DefaultFieldMatrixChangingVisitor(FieldElement zero) {
      this.zero = zero;
   }

   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
   }

   public FieldElement visit(int row, int column, FieldElement value) {
      return value;
   }

   public FieldElement end() {
      return this.zero;
   }
}
