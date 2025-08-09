package org.apache.commons.math3.linear;

public interface FieldDecompositionSolver {
   FieldVector solve(FieldVector var1);

   FieldMatrix solve(FieldMatrix var1);

   boolean isNonSingular();

   FieldMatrix getInverse();
}
