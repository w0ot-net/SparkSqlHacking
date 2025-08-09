package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleChecksumBlockId$ extends AbstractFunction3 implements Serializable {
   public static final ShuffleChecksumBlockId$ MODULE$ = new ShuffleChecksumBlockId$();

   public final String toString() {
      return "ShuffleChecksumBlockId";
   }

   public ShuffleChecksumBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleChecksumBlockId(shuffleId, mapId, reduceId);
   }

   public Option unapply(final ShuffleChecksumBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToLong(x$0.mapId()), BoxesRunTime.boxToInteger(x$0.reduceId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleChecksumBlockId$.class);
   }

   private ShuffleChecksumBlockId$() {
   }
}
