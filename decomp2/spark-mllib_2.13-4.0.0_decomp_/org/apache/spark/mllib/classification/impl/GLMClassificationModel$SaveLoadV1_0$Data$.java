package org.apache.spark.mllib.classification.impl;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class GLMClassificationModel$SaveLoadV1_0$Data$ extends AbstractFunction3 implements Serializable {
   public static final GLMClassificationModel$SaveLoadV1_0$Data$ MODULE$ = new GLMClassificationModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public GLMClassificationModel$SaveLoadV1_0$Data apply(final Vector weights, final double intercept, final Option threshold) {
      return new GLMClassificationModel$SaveLoadV1_0$Data(weights, intercept, threshold);
   }

   public Option unapply(final GLMClassificationModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.weights(), BoxesRunTime.boxToDouble(x$0.intercept()), x$0.threshold())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GLMClassificationModel$SaveLoadV1_0$Data$.class);
   }
}
