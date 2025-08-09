package org.apache.spark.ml.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.Array.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

public final class DenseMatrix$ implements Serializable {
   public static final DenseMatrix$ MODULE$ = new DenseMatrix$();

   public Option unapply(final DenseMatrix dm) {
      return new Some(new Tuple4(BoxesRunTime.boxToInteger(dm.numRows()), BoxesRunTime.boxToInteger(dm.numCols()), dm.values(), BoxesRunTime.boxToBoolean(dm.isTransposed())));
   }

   public DenseMatrix fromVectors(final Seq vectors) {
      int numRows = vectors.length();
      int numCols = ((Vector)vectors.head()).size();
      double[] values = (double[]).MODULE$.ofDim(numRows * numCols, scala.reflect.ClassTag..MODULE$.Double());
      IntRef offset = IntRef.create(0);

      for(int j = 0; j < numRows; ++j) {
         ((Vector)vectors.apply(j)).foreachNonZero((JFunction2.mcVID.sp)(i, v) -> values[offset.elem + i] = v);
         offset.elem += numCols;
      }

      return new DenseMatrix(numRows, numCols, values, true);
   }

   public DenseMatrix zeros(final int numRows, final int numCols) {
      scala.Predef..MODULE$.require((long)numRows * (long)numCols <= 2147483647L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, new double[numRows * numCols]);
   }

   public DenseMatrix ones(final int numRows, final int numCols) {
      scala.Predef..MODULE$.require((long)numRows * (long)numCols <= 2147483647L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[]).MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public DenseMatrix eye(final int n) {
      DenseMatrix identity = this.zeros(n, n);

      for(int i = 0; i < n; ++i) {
         identity.update(i, i, (double)1.0F);
      }

      return identity;
   }

   public DenseMatrix rand(final int numRows, final int numCols, final Random rng) {
      scala.Predef..MODULE$.require((long)numRows * (long)numCols <= 2147483647L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[]).MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> rng.nextDouble(), scala.reflect.ClassTag..MODULE$.Double()));
   }

   public DenseMatrix randn(final int numRows, final int numCols, final Random rng) {
      scala.Predef..MODULE$.require((long)numRows * (long)numCols <= 2147483647L, () -> numRows + " x " + numCols + " dense matrix is too large to allocate");
      return new DenseMatrix(numRows, numCols, (double[]).MODULE$.fill(numRows * numCols, (JFunction0.mcD.sp)() -> rng.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double()));
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
