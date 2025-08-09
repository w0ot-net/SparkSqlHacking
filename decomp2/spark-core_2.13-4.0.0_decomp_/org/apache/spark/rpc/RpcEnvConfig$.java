package org.apache.spark.rpc;

import java.io.Serializable;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RpcEnvConfig$ extends AbstractFunction8 implements Serializable {
   public static final RpcEnvConfig$ MODULE$ = new RpcEnvConfig$();

   public final String toString() {
      return "RpcEnvConfig";
   }

   public RpcEnvConfig apply(final SparkConf conf, final String name, final String bindAddress, final String advertiseAddress, final int port, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      return new RpcEnvConfig(conf, name, bindAddress, advertiseAddress, port, securityManager, numUsableCores, clientMode);
   }

   public Option unapply(final RpcEnvConfig x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(x$0.conf(), x$0.name(), x$0.bindAddress(), x$0.advertiseAddress(), BoxesRunTime.boxToInteger(x$0.port()), x$0.securityManager(), BoxesRunTime.boxToInteger(x$0.numUsableCores()), BoxesRunTime.boxToBoolean(x$0.clientMode()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcEnvConfig$.class);
   }

   private RpcEnvConfig$() {
   }
}
