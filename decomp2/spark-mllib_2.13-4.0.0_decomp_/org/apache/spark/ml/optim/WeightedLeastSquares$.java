package org.apache.spark.ml.optim;

import java.io.Serializable;
import scala.Product;
import scala.runtime.ModuleSerializationProxy;

public final class WeightedLeastSquares$ implements Serializable {
   public static final WeightedLeastSquares$ MODULE$ = new WeightedLeastSquares$();
   private static final int MAX_NUM_FEATURES = 4096;
   private static final Product[] supportedSolvers;

   static {
      supportedSolvers = (Product[])((Object[])(new Product[]{WeightedLeastSquares.Auto$.MODULE$, WeightedLeastSquares.Cholesky$.MODULE$, WeightedLeastSquares.QuasiNewton$.MODULE$}));
   }

   public WeightedLeastSquares.Solver $lessinit$greater$default$6() {
      return WeightedLeastSquares.Auto$.MODULE$;
   }

   public int $lessinit$greater$default$7() {
      return 100;
   }

   public double $lessinit$greater$default$8() {
      return 1.0E-6;
   }

   public int MAX_NUM_FEATURES() {
      return MAX_NUM_FEATURES;
   }

   public Product[] supportedSolvers() {
      return supportedSolvers;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WeightedLeastSquares$.class);
   }

   private WeightedLeastSquares$() {
   }
}
