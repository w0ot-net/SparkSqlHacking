package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class DistributedLDAModel$SaveLoadV1_0$EdgeData$ extends AbstractFunction3 implements Serializable {
   public static final DistributedLDAModel$SaveLoadV1_0$EdgeData$ MODULE$ = new DistributedLDAModel$SaveLoadV1_0$EdgeData$();

   public final String toString() {
      return "EdgeData";
   }

   public DistributedLDAModel$SaveLoadV1_0$EdgeData apply(final long srcId, final long dstId, final double tokenCounts) {
      return new DistributedLDAModel$SaveLoadV1_0$EdgeData(srcId, dstId, tokenCounts);
   }

   public Option unapply(final DistributedLDAModel$SaveLoadV1_0$EdgeData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToDouble(x$0.tokenCounts()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistributedLDAModel$SaveLoadV1_0$EdgeData$.class);
   }
}
