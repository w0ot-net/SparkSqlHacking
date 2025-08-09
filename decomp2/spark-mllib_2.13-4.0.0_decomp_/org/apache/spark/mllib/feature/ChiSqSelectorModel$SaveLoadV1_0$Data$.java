package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class ChiSqSelectorModel$SaveLoadV1_0$Data$ extends AbstractFunction1 implements Serializable {
   public static final ChiSqSelectorModel$SaveLoadV1_0$Data$ MODULE$ = new ChiSqSelectorModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public ChiSqSelectorModel$SaveLoadV1_0$Data apply(final int feature) {
      return new ChiSqSelectorModel$SaveLoadV1_0$Data(feature);
   }

   public Option unapply(final ChiSqSelectorModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.feature())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChiSqSelectorModel$SaveLoadV1_0$Data$.class);
   }
}
