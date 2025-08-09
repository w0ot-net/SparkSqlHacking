package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;

public interface FieldVector {
   Field getField();

   FieldVector copy();

   FieldVector add(FieldVector var1) throws DimensionMismatchException;

   FieldVector subtract(FieldVector var1) throws DimensionMismatchException;

   FieldVector mapAdd(FieldElement var1) throws NullArgumentException;

   FieldVector mapAddToSelf(FieldElement var1) throws NullArgumentException;

   FieldVector mapSubtract(FieldElement var1) throws NullArgumentException;

   FieldVector mapSubtractToSelf(FieldElement var1) throws NullArgumentException;

   FieldVector mapMultiply(FieldElement var1) throws NullArgumentException;

   FieldVector mapMultiplyToSelf(FieldElement var1) throws NullArgumentException;

   FieldVector mapDivide(FieldElement var1) throws NullArgumentException, MathArithmeticException;

   FieldVector mapDivideToSelf(FieldElement var1) throws NullArgumentException, MathArithmeticException;

   FieldVector mapInv() throws MathArithmeticException;

   FieldVector mapInvToSelf() throws MathArithmeticException;

   FieldVector ebeMultiply(FieldVector var1) throws DimensionMismatchException;

   FieldVector ebeDivide(FieldVector var1) throws DimensionMismatchException, MathArithmeticException;

   /** @deprecated */
   @Deprecated
   FieldElement[] getData();

   FieldElement dotProduct(FieldVector var1) throws DimensionMismatchException;

   FieldVector projection(FieldVector var1) throws DimensionMismatchException, MathArithmeticException;

   FieldMatrix outerProduct(FieldVector var1);

   FieldElement getEntry(int var1) throws OutOfRangeException;

   void setEntry(int var1, FieldElement var2) throws OutOfRangeException;

   int getDimension();

   FieldVector append(FieldVector var1);

   FieldVector append(FieldElement var1);

   FieldVector getSubVector(int var1, int var2) throws OutOfRangeException, NotPositiveException;

   void setSubVector(int var1, FieldVector var2) throws OutOfRangeException;

   void set(FieldElement var1);

   FieldElement[] toArray();
}
