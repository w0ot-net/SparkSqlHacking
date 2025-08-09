package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleBlockId$ extends AbstractFunction3 implements Serializable {
   public static final ShuffleBlockId$ MODULE$ = new ShuffleBlockId$();

   public final String toString() {
      return "ShuffleBlockId";
   }

   public ShuffleBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleBlockId(shuffleId, mapId, reduceId);
   }

   public Option unapply(final ShuffleBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToLong(x$0.mapId()), BoxesRunTime.boxToInteger(x$0.reduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleBlockId$.class);
   }

   private ShuffleBlockId$() {
   }
}
