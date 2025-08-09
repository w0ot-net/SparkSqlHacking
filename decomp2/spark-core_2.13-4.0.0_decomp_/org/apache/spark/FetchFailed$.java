package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.storage.BlockManagerId;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FetchFailed$ extends AbstractFunction6 implements Serializable {
   public static final FetchFailed$ MODULE$ = new FetchFailed$();

   public final String toString() {
      return "FetchFailed";
   }

   public FetchFailed apply(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message) {
      return new FetchFailed(bmAddress, shuffleId, mapId, mapIndex, reduceId, message);
   }

   public Option unapply(final FetchFailed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.bmAddress(), BoxesRunTime.boxToInteger(x$0.shuffleId()), BoxesRunTime.boxToLong(x$0.mapId()), BoxesRunTime.boxToInteger(x$0.mapIndex()), BoxesRunTime.boxToInteger(x$0.reduceId()), x$0.message())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FetchFailed$.class);
   }

   private FetchFailed$() {
   }
}
