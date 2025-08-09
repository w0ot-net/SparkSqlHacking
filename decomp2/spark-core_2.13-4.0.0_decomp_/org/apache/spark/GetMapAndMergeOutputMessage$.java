package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.rpc.RpcCallContext;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GetMapAndMergeOutputMessage$ extends AbstractFunction2 implements Serializable {
   public static final GetMapAndMergeOutputMessage$ MODULE$ = new GetMapAndMergeOutputMessage$();

   public final String toString() {
      return "GetMapAndMergeOutputMessage";
   }

   public GetMapAndMergeOutputMessage apply(final int shuffleId, final RpcCallContext context) {
      return new GetMapAndMergeOutputMessage(shuffleId, context);
   }

   public Option unapply(final GetMapAndMergeOutputMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.shuffleId()), x$0.context())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetMapAndMergeOutputMessage$.class);
   }

   private GetMapAndMergeOutputMessage$() {
   }
}
