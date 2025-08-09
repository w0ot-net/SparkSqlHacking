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

public final class GetMapOutputMessage$ extends AbstractFunction2 implements Serializable {
   public static final GetMapOutputMessage$ MODULE$ = new GetMapOutputMessage$();

   public final String toString() {
      return "GetMapOutputMessage";
   }

   public GetMapOutputMessage apply(final int shuffleId, final RpcCallContext context) {
      return new GetMapOutputMessage(shuffleId, context);
   }

   public Option unapply(final GetMapOutputMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.shuffleId()), x$0.context())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetMapOutputMessage$.class);
   }

   private GetMapOutputMessage$() {
   }
}
