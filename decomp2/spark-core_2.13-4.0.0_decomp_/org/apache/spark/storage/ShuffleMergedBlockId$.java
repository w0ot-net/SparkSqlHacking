package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleMergedBlockId$ extends AbstractFunction3 implements Serializable {
   public static final ShuffleMergedBlockId$ MODULE$ = new ShuffleMergedBlockId$();

   public final String toString() {
      return "ShuffleMergedBlockId";
   }

   public ShuffleMergedBlockId apply(final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return new ShuffleMergedBlockId(shuffleId, shuffleMergeId, reduceId);
   }

   public Option unapply(final ShuffleMergedBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToInteger(x$0.shuffleMergeId()), BoxesRunTime.boxToInteger(x$0.reduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleMergedBlockId$.class);
   }

   private ShuffleMergedBlockId$() {
   }
}
