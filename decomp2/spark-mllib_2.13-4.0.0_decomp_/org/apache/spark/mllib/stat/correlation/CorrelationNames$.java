package org.apache.spark.mllib.stat.correlation;

import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;

public final class CorrelationNames$ {
   public static final CorrelationNames$ MODULE$ = new CorrelationNames$();
   private static final Map nameToObjectMap;
   private static final String defaultCorrName;

   static {
      nameToObjectMap = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("pearson", PearsonCorrelation$.MODULE$), new Tuple2("spearman", SpearmanCorrelation$.MODULE$)})));
      defaultCorrName = "pearson";
   }

   public Map nameToObjectMap() {
      return nameToObjectMap;
   }

   public String defaultCorrName() {
      return defaultCorrName;
   }

   private CorrelationNames$() {
   }
}
