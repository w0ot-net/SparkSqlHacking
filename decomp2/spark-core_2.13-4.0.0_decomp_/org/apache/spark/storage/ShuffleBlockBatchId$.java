package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleBlockBatchId$ extends AbstractFunction4 implements Serializable {
   public static final ShuffleBlockBatchId$ MODULE$ = new ShuffleBlockBatchId$();

   public final String toString() {
      return "ShuffleBlockBatchId";
   }

   public ShuffleBlockBatchId apply(final int shuffleId, final long mapId, final int startReduceId, final int endReduceId) {
      return new ShuffleBlockBatchId(shuffleId, mapId, startReduceId, endReduceId);
   }

   public Option unapply(final ShuffleBlockBatchId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToLong(x$0.mapId()), BoxesRunTime.boxToInteger(x$0.startReduceId()), BoxesRunTime.boxToInteger(x$0.endReduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleBlockBatchId$.class);
   }

   private ShuffleBlockBatchId$() {
   }
}
