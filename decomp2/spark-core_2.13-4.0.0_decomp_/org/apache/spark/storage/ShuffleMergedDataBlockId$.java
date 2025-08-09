package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleMergedDataBlockId$ extends AbstractFunction4 implements Serializable {
   public static final ShuffleMergedDataBlockId$ MODULE$ = new ShuffleMergedDataBlockId$();

   public final String toString() {
      return "ShuffleMergedDataBlockId";
   }

   public ShuffleMergedDataBlockId apply(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return new ShuffleMergedDataBlockId(appId, shuffleId, shuffleMergeId, reduceId);
   }

   public Option unapply(final ShuffleMergedDataBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.appId(), BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToInteger(x$0.shuffleMergeId()), BoxesRunTime.boxToInteger(x$0.reduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleMergedDataBlockId$.class);
   }

   private ShuffleMergedDataBlockId$() {
   }
}
