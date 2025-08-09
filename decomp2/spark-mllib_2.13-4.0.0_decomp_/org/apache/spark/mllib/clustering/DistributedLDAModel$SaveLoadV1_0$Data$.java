package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class DistributedLDAModel$SaveLoadV1_0$Data$ extends AbstractFunction1 implements Serializable {
   public static final DistributedLDAModel$SaveLoadV1_0$Data$ MODULE$ = new DistributedLDAModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public DistributedLDAModel$SaveLoadV1_0$Data apply(final Vector globalTopicTotals) {
      return new DistributedLDAModel$SaveLoadV1_0$Data(globalTopicTotals);
   }

   public Option unapply(final DistributedLDAModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.globalTopicTotals()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistributedLDAModel$SaveLoadV1_0$Data$.class);
   }
}
