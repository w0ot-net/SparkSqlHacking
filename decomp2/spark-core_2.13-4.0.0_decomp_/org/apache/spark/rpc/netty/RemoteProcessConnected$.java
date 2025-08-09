package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RemoteProcessConnected$ extends AbstractFunction1 implements Serializable {
   public static final RemoteProcessConnected$ MODULE$ = new RemoteProcessConnected$();

   public final String toString() {
      return "RemoteProcessConnected";
   }

   public RemoteProcessConnected apply(final RpcAddress remoteAddress) {
      return new RemoteProcessConnected(remoteAddress);
   }

   public Option unapply(final RemoteProcessConnected x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.remoteAddress()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RemoteProcessConnected$.class);
   }

   private RemoteProcessConnected$() {
   }
}
