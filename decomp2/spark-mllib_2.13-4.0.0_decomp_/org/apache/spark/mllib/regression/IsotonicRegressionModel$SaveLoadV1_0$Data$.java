package org.apache.spark.mllib.regression;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public class IsotonicRegressionModel$SaveLoadV1_0$Data$ extends AbstractFunction2 implements Serializable {
   public static final IsotonicRegressionModel$SaveLoadV1_0$Data$ MODULE$ = new IsotonicRegressionModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public IsotonicRegressionModel$SaveLoadV1_0$Data apply(final double boundary, final double prediction) {
      return new IsotonicRegressionModel$SaveLoadV1_0$Data(boundary, prediction);
   }

   public Option unapply(final IsotonicRegressionModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.boundary(), x$0.prediction())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsotonicRegressionModel$SaveLoadV1_0$Data$.class);
   }
}
