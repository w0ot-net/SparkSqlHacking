package org.apache.spark.network.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.nio.ByteBuffer;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.HOST_PORT.;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.sasl.SaslRpcHandler;
import org.apache.spark.network.sasl.SecretKeyHolder;
import org.apache.spark.network.server.AbstractAuthRpcHandler;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

class AuthRpcHandler extends AbstractAuthRpcHandler {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(AuthRpcHandler.class);
   private final TransportConf conf;
   private final Channel channel;
   private final SecretKeyHolder secretKeyHolder;
   @VisibleForTesting
   SaslRpcHandler saslHandler;

   AuthRpcHandler(TransportConf conf, Channel channel, RpcHandler delegate, SecretKeyHolder secretKeyHolder) {
      super(delegate);
      this.conf = conf;
      this.channel = channel;
      this.secretKeyHolder = secretKeyHolder;
   }

   protected boolean doAuthChallenge(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      if (this.saslHandler != null) {
         return this.saslHandler.doAuthChallenge(client, message, callback);
      } else {
         int position = message.position();
         int limit = message.limit();

         AuthMessage challenge;
         try {
            challenge = AuthMessage.decodeMessage(message);
            LOG.debug("Received new auth challenge for client {}.", this.channel.remoteAddress());
         } catch (RuntimeException var20) {
            if (this.conf.saslFallback()) {
               LOG.warn("Failed to parse new auth challenge, reverting to SASL for client {}.", new MDC[]{MDC.of(.MODULE$, this.channel.remoteAddress())});
               this.saslHandler = new SaslRpcHandler(this.conf, this.channel, (RpcHandler)null, this.secretKeyHolder);
               message.position(position);
               message.limit(limit);
               return this.saslHandler.doAuthChallenge(client, message, callback);
            }

            LOG.debug("Unexpected challenge message from client {}, closing channel.", this.channel.remoteAddress());
            callback.onFailure(new IllegalArgumentException("Unknown challenge message."));
            this.channel.close();
            return false;
         }

         AuthEngine engine = null;

         label111: {
            boolean response;
            try {
               String secret = this.secretKeyHolder.getSecretKey(challenge.appId());
               Preconditions.checkState(secret != null, "Trying to authenticate non-registered app %s.", (Object)challenge.appId());
               LOG.debug("Authenticating challenge for app {}.", challenge.appId());
               engine = new AuthEngine(challenge.appId(), secret, this.conf);
               AuthMessage response = engine.response(challenge);
               ByteBuf responseData = Unpooled.buffer(response.encodedLength());
               response.encode(responseData);
               callback.onSuccess(responseData.nioBuffer());
               engine.sessionCipher().addToChannel(this.channel);
               client.setClientId(challenge.appId());
               break label111;
            } catch (Exception var21) {
               LOG.debug("Authentication failed for client {}, closing channel.", this.channel.remoteAddress());
               callback.onFailure(new IllegalArgumentException("Authentication failed."));
               this.channel.close();
               response = false;
            } finally {
               if (engine != null) {
                  try {
                     engine.close();
                  } catch (Exception e) {
                     Throwables.throwIfUnchecked(e);
                     throw new RuntimeException(e);
                  }
               }

            }

            return response;
         }

         LOG.debug("Authorization successful for client {}.", this.channel.remoteAddress());
         return true;
      }
   }
}
