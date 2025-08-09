package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class DenseMatrix$ implements Serializable {
   public static final DenseMatrix$ MODULE$ = new DenseMatrix$();

   public DenseMatrix zeros(final int numRows, final int numCols) {
      .MODULE$.require((long)numRows * (long)numCols <= 2147483632L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, new double[numRows * numCols]);
   }

   public DenseMatrix ones(final int numRows, final int numCols) {
      .MODULE$.require((long)numRows * (long)numCols <= 2147483632L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[])scala.Array..MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public DenseMatrix eye(final int n) {
      DenseMatrix identity = this.zeros(n, n);

      for(int i = 0; i < n; ++i) {
         identity.update(i, i, (double)1.0F);
      }

      return identity;
   }

   public DenseMatrix rand(final int numRows, final int numCols, final Random rng) {
      .MODULE$.require((long)numRows * (long)numCols <= 2147483632L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[])scala.Array..MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> rng.nextDouble(), scala.reflect.ClassTag..MODULE$.Double()));
   }

   public DenseMatrix randn(final int numRows, final int numCols, final Random rng) {
      .MODULE$.require((long)numRows * (long)numCols <= 2147483632L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[])scala.Array..MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> rng.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double()));
   }

   public DenseMatrix diag(final Vector vector) {
      int n = vector.size();
      DenseMatrix matrix = this.zeros(n, n);
      double[] values = vector.toArray();

      for(int i = 0; i < n; ++i) {
         matrix.update(i, i, values[i]);
      }

      return matrix;
   }

   public DenseMatrix fromML(final org.apache.spark.ml.linalg.DenseMatrix m) {
      return new DenseMatrix(m.numRows(), m.numCols(), m.values(), m.isTransposed());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseMatrix$.class);
   }

   private DenseMatrix$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
