package org.apache.spark.mllib.tree.impurity;

import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class Entropy$ implements Impurity {
   public static final Entropy$ MODULE$ = new Entropy$();
   private static final double _log2;

   static {
      _log2 = .MODULE$.log((double)2.0F);
   }

   private double _log2() {
      return _log2;
   }

   public double log2(final double x) {
      return .MODULE$.log(x) / this._log2();
   }

   public double calculate(final double[] counts, final double totalCount) {
      if (totalCount == (double)0) {
         return (double)0.0F;
      } else {
         int numClasses = counts.length;
         double impurity = (double)0.0F;

         for(int classIndex = 0; classIndex < numClasses; ++classIndex) {
            double classCount = counts[classIndex];
            if (classCount != (double)0) {
               double freq = classCount / totalCount;
               impurity -= freq * this.log2(freq);
            }
         }

         return impurity;
      }
   }

   public double calculate(final double count, final double sum, final double sumSquares) {
      throw new UnsupportedOperationException("Entropy.calculate");
   }

   public Entropy$ instance() {
      return this;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Entropy$.class);
   }

   private Entropy$() {
   }
}
