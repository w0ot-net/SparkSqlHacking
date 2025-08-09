package org.apache.spark.shuffle;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleBlockInfo$ extends AbstractFunction2 implements Serializable {
   public static final ShuffleBlockInfo$ MODULE$ = new ShuffleBlockInfo$();

   public final String toString() {
      return "ShuffleBlockInfo";
   }

   public ShuffleBlockInfo apply(final int shuffleId, final long mapId) {
      return new ShuffleBlockInfo(shuffleId, mapId);
   }

   public Option unapply(final ShuffleBlockInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIJ.sp(x$0.shuffleId(), x$0.mapId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleBlockInfo$.class);
   }

   private ShuffleBlockInfo$() {
   }
}
