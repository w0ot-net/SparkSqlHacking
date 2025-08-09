package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public class DefaultFieldMatrixPreservingVisitor implements FieldMatrixPreservingVisitor {
   private final FieldElement zero;

   public DefaultFieldMatrixPreservingVisitor(FieldElement zero) {
      this.zero = zero;
   }

   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
   }

   public void visit(int row, int column, FieldElement value) {
   }

   public FieldElement end() {
      return this.zero;
   }
}
