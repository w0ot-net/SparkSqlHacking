package org.apache.spark.rpc;

import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.rpc.netty.NettyRpcEnvFactory;

public final class RpcEnv$ {
   public static final RpcEnv$ MODULE$ = new RpcEnv$();

   public RpcEnv create(final String name, final String host, final int port, final SparkConf conf, final SecurityManager securityManager, final boolean clientMode) {
      return this.create(name, host, host, port, conf, securityManager, 0, clientMode);
   }

   public RpcEnv create(final String name, final String bindAddress, final String advertiseAddress, final int port, final SparkConf conf, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      RpcEnvConfig config = new RpcEnvConfig(conf, name, bindAddress, advertiseAddress, port, securityManager, numUsableCores, clientMode);
      return (new NettyRpcEnvFactory()).create(config);
   }

   public boolean create$default$6() {
      return false;
   }

   private RpcEnv$() {
   }
}
