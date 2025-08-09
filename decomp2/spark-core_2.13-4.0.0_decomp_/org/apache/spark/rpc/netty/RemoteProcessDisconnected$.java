package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RemoteProcessDisconnected$ extends AbstractFunction1 implements Serializable {
   public static final RemoteProcessDisconnected$ MODULE$ = new RemoteProcessDisconnected$();

   public final String toString() {
      return "RemoteProcessDisconnected";
   }

   public RemoteProcessDisconnected apply(final RpcAddress remoteAddress) {
      return new RemoteProcessDisconnected(remoteAddress);
   }

   public Option unapply(final RemoteProcessDisconnected x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.remoteAddress()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RemoteProcessDisconnected$.class);
   }

   private RemoteProcessDisconnected$() {
   }
}
