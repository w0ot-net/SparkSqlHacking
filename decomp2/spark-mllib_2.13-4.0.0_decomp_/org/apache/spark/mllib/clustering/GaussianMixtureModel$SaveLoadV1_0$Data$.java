package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class GaussianMixtureModel$SaveLoadV1_0$Data$ extends AbstractFunction3 implements Serializable {
   public static final GaussianMixtureModel$SaveLoadV1_0$Data$ MODULE$ = new GaussianMixtureModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public GaussianMixtureModel$SaveLoadV1_0$Data apply(final double weight, final Vector mu, final Matrix sigma) {
      return new GaussianMixtureModel$SaveLoadV1_0$Data(weight, mu, sigma);
   }

   public Option unapply(final GaussianMixtureModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.weight()), x$0.mu(), x$0.sigma())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GaussianMixtureModel$SaveLoadV1_0$Data$.class);
   }
}
