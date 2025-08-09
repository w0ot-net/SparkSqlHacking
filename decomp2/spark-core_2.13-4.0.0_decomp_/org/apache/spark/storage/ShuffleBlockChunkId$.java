package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleBlockChunkId$ extends AbstractFunction4 implements Serializable {
   public static final ShuffleBlockChunkId$ MODULE$ = new ShuffleBlockChunkId$();

   public final String toString() {
      return "ShuffleBlockChunkId";
   }

   public ShuffleBlockChunkId apply(final int shuffleId, final int shuffleMergeId, final int reduceId, final int chunkId) {
      return new ShuffleBlockChunkId(shuffleId, shuffleMergeId, reduceId, chunkId);
   }

   public Option unapply(final ShuffleBlockChunkId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToInteger(x$0.shuffleMergeId()), BoxesRunTime.boxToInteger(x$0.reduceId()), BoxesRunTime.boxToInteger(x$0.chunkId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleBlockChunkId$.class);
   }

   private ShuffleBlockChunkId$() {
   }
}
