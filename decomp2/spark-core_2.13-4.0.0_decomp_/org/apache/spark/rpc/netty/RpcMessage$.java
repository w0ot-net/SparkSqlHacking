package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class RpcMessage$ extends AbstractFunction3 implements Serializable {
   public static final RpcMessage$ MODULE$ = new RpcMessage$();

   public final String toString() {
      return "RpcMessage";
   }

   public RpcMessage apply(final RpcAddress senderAddress, final Object content, final NettyRpcCallContext context) {
      return new RpcMessage(senderAddress, content, context);
   }

   public Option unapply(final RpcMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.senderAddress(), x$0.content(), x$0.context())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcMessage$.class);
   }

   private RpcMessage$() {
   }
}
