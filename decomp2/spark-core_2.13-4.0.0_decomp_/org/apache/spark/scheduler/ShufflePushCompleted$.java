package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShufflePushCompleted$ extends AbstractFunction3 implements Serializable {
   public static final ShufflePushCompleted$ MODULE$ = new ShufflePushCompleted$();

   public final String toString() {
      return "ShufflePushCompleted";
   }

   public ShufflePushCompleted apply(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      return new ShufflePushCompleted(shuffleId, shuffleMergeId, mapIndex);
   }

   public Option unapply(final ShufflePushCompleted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToInteger(x$0.shuffleMergeId()), BoxesRunTime.boxToInteger(x$0.mapIndex()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShufflePushCompleted$.class);
   }

   private ShufflePushCompleted$() {
   }
}
