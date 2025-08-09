package org.apache.spark.ml.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.BLAS$;
import scala.Predef.;

public final class Utils$ {
   public static final Utils$ MODULE$ = new Utils$();
   private static double EPSILON;
   private static volatile boolean bitmap$0;

   private double EPSILON$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            double eps;
            for(eps = (double)1.0F; (double)1.0F + eps / (double)2.0F != (double)1.0F; eps /= (double)2.0F) {
            }

            EPSILON = eps;
            bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return EPSILON;
   }

   public double EPSILON() {
      return !bitmap$0 ? this.EPSILON$lzycompute() : EPSILON;
   }

   public double[] unpackUpperTriangular(final int n, final double[] triangularValues) {
      double[] symmetricValues = new double[n * n];
      int r = 0;

      for(int i = 0; i < n; ++i) {
         for(int j = 0; j <= i; ++j) {
            symmetricValues[i * n + j] = triangularValues[r];
            symmetricValues[j * n + i] = triangularValues[r];
            ++r;
         }
      }

      return symmetricValues;
   }

   public int indexUpperTriangular(final int n, final int i, final int j) {
      .MODULE$.require(i >= 0 && i < n, () -> "Expected 0 <= i < " + n + ", got i = " + i + ".");
      .MODULE$.require(j >= 0 && j < n, () -> "Expected 0 <= j < " + n + ", got j = " + j + ".");
      return i <= j ? j * (j + 1) / 2 + i : i * (i + 1) / 2 + j;
   }

   public double log1pExp(final double x) {
      return x > (double)0 ? x + scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(-x)) : scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(x));
   }

   public void softmax(final double[] array) {
      this.softmax(array, array.length, 0, 1, array);
   }

   public void softmax(final double[] input, final int n, final int offset, final int step, final double[] output) {
      double maxValue = -Double.MAX_VALUE;
      int i = offset;

      int end;
      for(end = offset + step * n; i < end; i += step) {
         double v = input[i];
         if (scala.runtime.RichDouble..MODULE$.isPosInfinity$extension(.MODULE$.doubleWrapper(v))) {
            BLAS$.MODULE$.javaBLAS().dscal(n, (double)0.0F, output, offset, step);
            output[i] = (double)1.0F;
            return;
         }

         if (v > maxValue) {
            maxValue = v;
         }
      }

      double sum = (double)0.0F;

      for(int var16 = offset; var16 < end; var16 += step) {
         double exp = scala.math.package..MODULE$.exp(input[var16] - maxValue);
         output[var16] = exp;
         sum += exp;
      }

      BLAS$.MODULE$.javaBLAS().dscal(n, (double)1.0F / sum, output, offset, step);
   }

   private Utils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
