package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class OneWayMessage$ extends AbstractFunction2 implements Serializable {
   public static final OneWayMessage$ MODULE$ = new OneWayMessage$();

   public final String toString() {
      return "OneWayMessage";
   }

   public OneWayMessage apply(final RpcAddress senderAddress, final Object content) {
      return new OneWayMessage(senderAddress, content);
   }

   public Option unapply(final OneWayMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.senderAddress(), x$0.content())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneWayMessage$.class);
   }

   private OneWayMessage$() {
   }
}
