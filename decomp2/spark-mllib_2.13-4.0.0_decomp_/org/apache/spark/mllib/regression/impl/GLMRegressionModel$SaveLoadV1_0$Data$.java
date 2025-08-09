package org.apache.spark.mllib.regression.impl;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class GLMRegressionModel$SaveLoadV1_0$Data$ extends AbstractFunction2 implements Serializable {
   public static final GLMRegressionModel$SaveLoadV1_0$Data$ MODULE$ = new GLMRegressionModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public GLMRegressionModel$SaveLoadV1_0$Data apply(final Vector weights, final double intercept) {
      return new GLMRegressionModel$SaveLoadV1_0$Data(weights, intercept);
   }

   public Option unapply(final GLMRegressionModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.weights(), BoxesRunTime.boxToDouble(x$0.intercept()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GLMRegressionModel$SaveLoadV1_0$Data$.class);
   }
}
