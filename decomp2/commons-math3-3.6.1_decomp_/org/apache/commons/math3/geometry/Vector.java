package org.apache.commons.math3.geometry;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;

public interface Vector extends Point {
   Vector getZero();

   double getNorm1();

   double getNorm();

   double getNormSq();

   double getNormInf();

   Vector add(Vector var1);

   Vector add(double var1, Vector var3);

   Vector subtract(Vector var1);

   Vector subtract(double var1, Vector var3);

   Vector negate();

   Vector normalize() throws MathArithmeticException;

   Vector scalarMultiply(double var1);

   boolean isInfinite();

   double distance1(Vector var1);

   double distance(Vector var1);

   double distanceInf(Vector var1);

   double distanceSq(Vector var1);

   double dotProduct(Vector var1);

   String toString(NumberFormat var1);
}
