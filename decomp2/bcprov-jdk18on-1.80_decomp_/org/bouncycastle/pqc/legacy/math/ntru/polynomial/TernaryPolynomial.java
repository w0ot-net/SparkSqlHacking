package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

public interface TernaryPolynomial extends Polynomial {
   IntegerPolynomial mult(IntegerPolynomial var1);

   int[] getOnes();

   int[] getNegOnes();

   int size();

   void clear();
}
