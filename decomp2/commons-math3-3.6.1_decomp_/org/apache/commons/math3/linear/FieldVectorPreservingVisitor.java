package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

public interface FieldVectorPreservingVisitor {
   void start(int var1, int var2, int var3);

   void visit(int var1, FieldElement var2);

   FieldElement end();
}
