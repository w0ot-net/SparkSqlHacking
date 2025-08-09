package org.apache.spark.rpc.netty;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class RpcOutboxMessage$ extends AbstractFunction3 implements Serializable {
   public static final RpcOutboxMessage$ MODULE$ = new RpcOutboxMessage$();

   public final String toString() {
      return "RpcOutboxMessage";
   }

   public RpcOutboxMessage apply(final ByteBuffer content, final Function1 _onFailure, final Function2 _onSuccess) {
      return new RpcOutboxMessage(content, _onFailure, _onSuccess);
   }

   public Option unapply(final RpcOutboxMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.content(), x$0._onFailure(), x$0._onSuccess())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcOutboxMessage$.class);
   }

   private RpcOutboxMessage$() {
   }
}
