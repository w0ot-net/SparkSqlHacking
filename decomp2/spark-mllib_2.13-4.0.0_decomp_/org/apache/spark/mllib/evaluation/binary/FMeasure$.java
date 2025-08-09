package org.apache.spark.mllib.evaluation.binary;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FMeasure$ extends AbstractFunction1 implements Serializable {
   public static final FMeasure$ MODULE$ = new FMeasure$();

   public final String toString() {
      return "FMeasure";
   }

   public FMeasure apply(final double beta) {
      return new FMeasure(beta);
   }

   public Option unapply(final FMeasure x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.beta())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FMeasure$.class);
   }

   private FMeasure$() {
   }
}
