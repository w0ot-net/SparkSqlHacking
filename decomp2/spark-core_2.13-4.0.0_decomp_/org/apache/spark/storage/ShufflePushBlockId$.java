package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShufflePushBlockId$ extends AbstractFunction4 implements Serializable {
   public static final ShufflePushBlockId$ MODULE$ = new ShufflePushBlockId$();

   public final String toString() {
      return "ShufflePushBlockId";
   }

   public ShufflePushBlockId apply(final int shuffleId, final int shuffleMergeId, final int mapIndex, final int reduceId) {
      return new ShufflePushBlockId(shuffleId, shuffleMergeId, mapIndex, reduceId);
   }

   public Option unapply(final ShufflePushBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToInteger(x$0.shuffleMergeId()), BoxesRunTime.boxToInteger(x$0.mapIndex()), BoxesRunTime.boxToInteger(x$0.reduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShufflePushBlockId$.class);
   }

   private ShufflePushBlockId$() {
   }
}
