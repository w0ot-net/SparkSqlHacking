package org.apache.spark.util;

import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.rpc.RpcTimeout$;
import scala.collection.immutable.Seq;
import scala.concurrent.duration.package.;
import scala.runtime.BoxesRunTime;

public final class RpcUtils$ {
   public static final RpcUtils$ MODULE$ = new RpcUtils$();
   private static final RpcTimeout INFINITE_TIMEOUT;
   private static final int MAX_MESSAGE_SIZE_IN_MB;

   static {
      INFINITE_TIMEOUT = new RpcTimeout((new scala.concurrent.duration.package.DurationLong(.MODULE$.DurationLong(Long.MAX_VALUE))).nanos(), "infinite");
      MAX_MESSAGE_SIZE_IN_MB = 2047;
   }

   public RpcEndpointRef makeDriverRef(final String name, final SparkConf conf, final RpcEnv rpcEnv) {
      String driverHost = conf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS().key(), "localhost");
      int driverPort = conf.getInt(org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT().key(), 7077);
      Utils$.MODULE$.checkHost(driverHost);
      return rpcEnv.setupEndpointRef(RpcAddress$.MODULE$.apply(driverHost, driverPort), name);
   }

   public RpcTimeout askRpcTimeout(final SparkConf conf) {
      return RpcTimeout$.MODULE$.apply(conf, (Seq)(new scala.collection.immutable..colon.colon(Network$.MODULE$.RPC_ASK_TIMEOUT().key(), new scala.collection.immutable..colon.colon(Network$.MODULE$.NETWORK_TIMEOUT().key(), scala.collection.immutable.Nil..MODULE$))), "120s");
   }

   public RpcTimeout lookupRpcTimeout(final SparkConf conf) {
      return RpcTimeout$.MODULE$.apply(conf, (Seq)(new scala.collection.immutable..colon.colon(Network$.MODULE$.RPC_LOOKUP_TIMEOUT().key(), new scala.collection.immutable..colon.colon(Network$.MODULE$.NETWORK_TIMEOUT().key(), scala.collection.immutable.Nil..MODULE$))), "120s");
   }

   public RpcTimeout INFINITE_TIMEOUT() {
      return INFINITE_TIMEOUT;
   }

   private int MAX_MESSAGE_SIZE_IN_MB() {
      return MAX_MESSAGE_SIZE_IN_MB;
   }

   public int maxMessageSizeBytes(final SparkConf conf) {
      int maxSizeInMB = BoxesRunTime.unboxToInt(conf.get(Network$.MODULE$.RPC_MESSAGE_MAX_SIZE()));
      if (maxSizeInMB > this.MAX_MESSAGE_SIZE_IN_MB()) {
         String var10002 = Network$.MODULE$.RPC_MESSAGE_MAX_SIZE().key();
         throw new IllegalArgumentException(var10002 + " should not be greater than " + this.MAX_MESSAGE_SIZE_IN_MB() + " MB");
      } else {
         return maxSizeInMB * 1024 * 1024;
      }
   }

   private RpcUtils$() {
   }
}
