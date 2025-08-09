package org.apache.spark.mllib.tree.impurity;

import scala.runtime.ModuleSerializationProxy;

public final class Gini$ implements Impurity {
   public static final Gini$ MODULE$ = new Gini$();

   public double calculate(final double[] counts, final double totalCount) {
      if (totalCount == (double)0) {
         return (double)0.0F;
      } else {
         int numClasses = counts.length;
         double impurity = (double)1.0F;

         for(int classIndex = 0; classIndex < numClasses; ++classIndex) {
            double freq = counts[classIndex] / totalCount;
            impurity -= freq * freq;
         }

         return impurity;
      }
   }

   public double calculate(final double count, final double sum, final double sumSquares) {
      throw new UnsupportedOperationException("Gini.calculate");
   }

   public Gini$ instance() {
      return this;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Gini$.class);
   }

   private Gini$() {
   }
}
