package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class RemoteProcessConnectionError$ extends AbstractFunction2 implements Serializable {
   public static final RemoteProcessConnectionError$ MODULE$ = new RemoteProcessConnectionError$();

   public final String toString() {
      return "RemoteProcessConnectionError";
   }

   public RemoteProcessConnectionError apply(final Throwable cause, final RpcAddress remoteAddress) {
      return new RemoteProcessConnectionError(cause, remoteAddress);
   }

   public Option unapply(final RemoteProcessConnectionError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.cause(), x$0.remoteAddress())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RemoteProcessConnectionError$.class);
   }

   private RemoteProcessConnectionError$() {
   }
}
