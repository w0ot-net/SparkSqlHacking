package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public interface FieldMatrixChangingVisitor {
   void start(int var1, int var2, int var3, int var4, int var5, int var6);

   FieldElement visit(int var1, int var2, FieldElement var3);

   FieldElement end();
}
