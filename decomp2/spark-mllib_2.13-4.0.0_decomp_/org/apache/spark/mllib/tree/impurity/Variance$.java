package org.apache.spark.mllib.tree.impurity;

import scala.runtime.ModuleSerializationProxy;

public final class Variance$ implements Impurity {
   public static final Variance$ MODULE$ = new Variance$();

   public double calculate(final double[] counts, final double totalCount) {
      throw new UnsupportedOperationException("Variance.calculate");
   }

   public double calculate(final double count, final double sum, final double sumSquares) {
      if (count == (double)0) {
         return (double)0.0F;
      } else {
         double squaredLoss = sumSquares - sum * sum / count;
         return squaredLoss / count;
      }
   }

   public Variance$ instance() {
      return this;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Variance$.class);
   }

   private Variance$() {
   }
}
