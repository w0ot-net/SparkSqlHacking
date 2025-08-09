package org.apache.spark.mllib.classification;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public class NaiveBayesModel$SaveLoadV2_0$Data$ extends AbstractFunction4 implements Serializable {
   public static final NaiveBayesModel$SaveLoadV2_0$Data$ MODULE$ = new NaiveBayesModel$SaveLoadV2_0$Data$();

   public final String toString() {
      return "Data";
   }

   public NaiveBayesModel$SaveLoadV2_0$Data apply(final double[] labels, final double[] pi, final double[][] theta, final String modelType) {
      return new NaiveBayesModel$SaveLoadV2_0$Data(labels, pi, theta, modelType);
   }

   public Option unapply(final NaiveBayesModel$SaveLoadV2_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.labels(), x$0.pi(), x$0.theta(), x$0.modelType())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayesModel$SaveLoadV2_0$Data$.class);
   }
}
