package org.apache.spark.network.sasl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.server.AbstractAuthRpcHandler;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.TransportConf;

public class SaslRpcHandler extends AbstractAuthRpcHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SaslRpcHandler.class);
   private final TransportConf conf;
   private final Channel channel;
   private final SecretKeyHolder secretKeyHolder;
   private SparkSaslServer saslServer;

   public SaslRpcHandler(TransportConf conf, Channel channel, RpcHandler delegate, SecretKeyHolder secretKeyHolder) {
      super(delegate);
      this.conf = conf;
      this.channel = channel;
      this.secretKeyHolder = secretKeyHolder;
      this.saslServer = null;
   }

   public boolean doAuthChallenge(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      if (this.saslServer == null || !this.saslServer.isComplete()) {
         ByteBuf nettyBuf = Unpooled.wrappedBuffer(message);

         SaslMessage saslMessage;
         try {
            saslMessage = SaslMessage.decode(nettyBuf);
         } finally {
            nettyBuf.release();
         }

         if (this.saslServer == null) {
            client.setClientId(saslMessage.appId);
            this.saslServer = new SparkSaslServer(saslMessage.appId, this.secretKeyHolder, this.conf.saslServerAlwaysEncrypt());
         }

         byte[] response;
         try {
            response = this.saslServer.response(JavaUtils.bufferToArray(saslMessage.body().nioByteBuffer()));
         } catch (IOException ioe) {
            throw new RuntimeException(ioe);
         }

         callback.onSuccess(ByteBuffer.wrap(response));
      }

      if (this.saslServer.isComplete()) {
         if (!"auth-conf".equals(this.saslServer.getNegotiatedProperty("javax.security.sasl.qop"))) {
            logger.debug("SASL authentication successful for channel {}", client);
            this.complete(true);
            return true;
         } else {
            logger.debug("Enabling encryption for channel {}", client);
            SaslEncryption.addToChannel(this.channel, this.saslServer, this.conf.maxSaslEncryptedBlockSize());
            this.complete(false);
            return true;
         }
      } else {
         return false;
      }
   }

   public void channelInactive(TransportClient client) {
      try {
         super.channelInactive(client);
      } finally {
         if (this.saslServer != null) {
            this.saslServer.dispose();
         }

      }

   }

   private void complete(boolean dispose) {
      if (dispose) {
         try {
            this.saslServer.dispose();
         } catch (RuntimeException e) {
            logger.error("Error while disposing SASL server", e);
         }
      }

      this.saslServer = null;
   }
}
