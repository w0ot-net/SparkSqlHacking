package org.apache.spark.mllib.classification;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public class NaiveBayesModel$SaveLoadV1_0$Data$ extends AbstractFunction3 implements Serializable {
   public static final NaiveBayesModel$SaveLoadV1_0$Data$ MODULE$ = new NaiveBayesModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public NaiveBayesModel$SaveLoadV1_0$Data apply(final double[] labels, final double[] pi, final double[][] theta) {
      return new NaiveBayesModel$SaveLoadV1_0$Data(labels, pi, theta);
   }

   public Option unapply(final NaiveBayesModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.labels(), x$0.pi(), x$0.theta())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayesModel$SaveLoadV1_0$Data$.class);
   }
}
