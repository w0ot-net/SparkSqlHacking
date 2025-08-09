package org.apache.spark.rpc.netty;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RpcFailure$ extends AbstractFunction1 implements Serializable {
   public static final RpcFailure$ MODULE$ = new RpcFailure$();

   public final String toString() {
      return "RpcFailure";
   }

   public RpcFailure apply(final Throwable e) {
      return new RpcFailure(e);
   }

   public Option unapply(final RpcFailure x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.e()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcFailure$.class);
   }

   private RpcFailure$() {
   }
}
