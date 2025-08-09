package org.apache.spark.network.crypto;

import io.netty.channel.Channel;
import org.apache.spark.network.sasl.SaslServerBootstrap;
import org.apache.spark.network.sasl.SecretKeyHolder;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.TransportServerBootstrap;
import org.apache.spark.network.util.TransportConf;

public class AuthServerBootstrap implements TransportServerBootstrap {
   private final TransportConf conf;
   private final SecretKeyHolder secretKeyHolder;

   public AuthServerBootstrap(TransportConf conf, SecretKeyHolder secretKeyHolder) {
      this.conf = conf;
      this.secretKeyHolder = secretKeyHolder;
   }

   public RpcHandler doBootstrap(Channel channel, RpcHandler rpcHandler) {
      if (!this.conf.encryptionEnabled()) {
         TransportServerBootstrap sasl = new SaslServerBootstrap(this.conf, this.secretKeyHolder);
         return sasl.doBootstrap(channel, rpcHandler);
      } else {
         return new AuthRpcHandler(this.conf, channel, rpcHandler, this.secretKeyHolder);
      }
   }
}
