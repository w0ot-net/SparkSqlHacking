package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;

public interface FieldMatrix extends AnyMatrix {
   Field getField();

   FieldMatrix createMatrix(int var1, int var2) throws NotStrictlyPositiveException;

   FieldMatrix copy();

   FieldMatrix add(FieldMatrix var1) throws MatrixDimensionMismatchException;

   FieldMatrix subtract(FieldMatrix var1) throws MatrixDimensionMismatchException;

   FieldMatrix scalarAdd(FieldElement var1);

   FieldMatrix scalarMultiply(FieldElement var1);

   FieldMatrix multiply(FieldMatrix var1) throws DimensionMismatchException;

   FieldMatrix preMultiply(FieldMatrix var1) throws DimensionMismatchException;

   FieldMatrix power(int var1) throws NonSquareMatrixException, NotPositiveException;

   FieldElement[][] getData();

   FieldMatrix getSubMatrix(int var1, int var2, int var3, int var4) throws NumberIsTooSmallException, OutOfRangeException;

   FieldMatrix getSubMatrix(int[] var1, int[] var2) throws NoDataException, NullArgumentException, OutOfRangeException;

   void copySubMatrix(int var1, int var2, int var3, int var4, FieldElement[][] var5) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException;

   void copySubMatrix(int[] var1, int[] var2, FieldElement[][] var3) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException;

   void setSubMatrix(FieldElement[][] var1, int var2, int var3) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException;

   FieldMatrix getRowMatrix(int var1) throws OutOfRangeException;

   void setRowMatrix(int var1, FieldMatrix var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldMatrix getColumnMatrix(int var1) throws OutOfRangeException;

   void setColumnMatrix(int var1, FieldMatrix var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldVector getRowVector(int var1) throws OutOfRangeException;

   void setRowVector(int var1, FieldVector var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldVector getColumnVector(int var1) throws OutOfRangeException;

   void setColumnVector(int var1, FieldVector var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldElement[] getRow(int var1) throws OutOfRangeException;

   void setRow(int var1, FieldElement[] var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldElement[] getColumn(int var1) throws OutOfRangeException;

   void setColumn(int var1, FieldElement[] var2) throws MatrixDimensionMismatchException, OutOfRangeException;

   FieldElement getEntry(int var1, int var2) throws OutOfRangeException;

   void setEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   void addToEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   void multiplyEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   FieldMatrix transpose();

   FieldElement getTrace() throws NonSquareMatrixException;

   FieldElement[] operate(FieldElement[] var1) throws DimensionMismatchException;

   FieldVector operate(FieldVector var1) throws DimensionMismatchException;

   FieldElement[] preMultiply(FieldElement[] var1) throws DimensionMismatchException;

   FieldVector preMultiply(FieldVector var1) throws DimensionMismatchException;

   FieldElement walkInRowOrder(FieldMatrixChangingVisitor var1);

   FieldElement walkInRowOrder(FieldMatrixPreservingVisitor var1);

   FieldElement walkInRowOrder(FieldMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

   FieldElement walkInRowOrder(FieldMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

   FieldElement walkInColumnOrder(FieldMatrixChangingVisitor var1);

   FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor var1);

   FieldElement walkInColumnOrder(FieldMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

   FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

   FieldElement walkInOptimizedOrder(FieldMatrixChangingVisitor var1);

   FieldElement walkInOptimizedOrder(FieldMatrixPreservingVisitor var1);

   FieldElement walkInOptimizedOrder(FieldMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

   FieldElement walkInOptimizedOrder(FieldMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;
}
