package org.apache.spark.mllib.stat.correlation;

import java.util.NoSuchElementException;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import scala.collection.Iterable;

public final class Correlations$ {
   public static final Correlations$ MODULE$ = new Correlations$();

   public double corr(final RDD x, final RDD y, final String method) {
      Correlation correlation = this.getCorrelationFromName(method);
      return correlation.computeCorrelation(x, y);
   }

   public String corr$default$3() {
      return CorrelationNames$.MODULE$.defaultCorrName();
   }

   public Matrix corrMatrix(final RDD X, final String method) {
      Correlation correlation = this.getCorrelationFromName(method);
      return correlation.computeCorrelationMatrix(X);
   }

   public String corrMatrix$default$2() {
      return CorrelationNames$.MODULE$.defaultCorrName();
   }

   public Correlation getCorrelationFromName(final String method) {
      try {
         return (Correlation)CorrelationNames$.MODULE$.nameToObjectMap().apply(method);
      } catch (NoSuchElementException var3) {
         Iterable var10002 = CorrelationNames$.MODULE$.nameToObjectMap().keys();
         throw new IllegalArgumentException("Unrecognized method name. Supported correlations: " + var10002.mkString(", "));
      }
   }

   private Correlations$() {
   }
}
