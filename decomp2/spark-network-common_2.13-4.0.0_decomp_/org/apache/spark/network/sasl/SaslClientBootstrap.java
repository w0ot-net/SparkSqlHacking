package org.apache.spark.network.sasl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeoutException;
import javax.security.sasl.SaslException;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientBootstrap;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.TransportConf;

public class SaslClientBootstrap implements TransportClientBootstrap {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SaslClientBootstrap.class);
   private final TransportConf conf;
   private final String appId;
   private final SecretKeyHolder secretKeyHolder;

   public SaslClientBootstrap(TransportConf conf, String appId, SecretKeyHolder secretKeyHolder) {
      this.conf = conf;
      this.appId = appId;
      this.secretKeyHolder = secretKeyHolder;
   }

   public void doBootstrap(TransportClient client, Channel channel) {
      SparkSaslClient saslClient = new SparkSaslClient(this.appId, this.secretKeyHolder, this.conf.saslEncryption());

      try {
         ByteBuffer response;
         for(byte[] payload = saslClient.firstToken(); !saslClient.isComplete(); payload = saslClient.response(JavaUtils.bufferToArray(response))) {
            SaslMessage msg = new SaslMessage(this.appId, payload);
            ByteBuf buf = Unpooled.buffer(msg.encodedLength() + (int)msg.body().size());
            msg.encode(buf);
            buf.writeBytes(msg.body().nioByteBuffer());

            try {
               response = client.sendRpcSync(buf.nioBuffer(), (long)this.conf.authRTTimeoutMs());
            } catch (RuntimeException ex) {
               Throwable var10 = ex.getCause();
               if (var10 instanceof TimeoutException te) {
                  throw new SaslTimeoutException(te);
               }

               throw ex;
            }
         }

         client.setClientId(this.appId);
         if (this.conf.saslEncryption()) {
            if (!"auth-conf".equals(saslClient.getNegotiatedProperty("javax.security.sasl.qop"))) {
               throw new RuntimeException(new SaslException("Encryption requests by negotiated non-encrypted connection."));
            }

            SaslEncryption.addToChannel(channel, saslClient, this.conf.maxSaslEncryptedBlockSize());
            saslClient = null;
            logger.debug("Channel {} configured for encryption.", client);
         }
      } catch (IOException ioe) {
         throw new RuntimeException(ioe);
      } finally {
         if (saslClient != null) {
            try {
               saslClient.dispose();
            } catch (RuntimeException e) {
               logger.error("Error while disposing SASL client", e);
            }
         }

      }

   }
}
