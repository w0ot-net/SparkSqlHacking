package org.apache.spark.network.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeoutException;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientBootstrap;
import org.apache.spark.network.sasl.SaslClientBootstrap;
import org.apache.spark.network.sasl.SecretKeyHolder;
import org.apache.spark.network.util.TransportConf;

public class AuthClientBootstrap implements TransportClientBootstrap {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(AuthClientBootstrap.class);
   private final TransportConf conf;
   private final String appId;
   private final SecretKeyHolder secretKeyHolder;

   public AuthClientBootstrap(TransportConf conf, String appId, SecretKeyHolder secretKeyHolder) {
      this.conf = conf;
      this.appId = appId;
      this.secretKeyHolder = secretKeyHolder;
   }

   public void doBootstrap(TransportClient client, Channel channel) {
      if (!this.conf.encryptionEnabled()) {
         LOG.debug("AES encryption disabled, using old auth protocol.");
         this.doSaslAuth(client, channel);
      } else {
         try {
            this.doSparkAuth(client, channel);
            client.setClientId(this.appId);
         } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
         } catch (RuntimeException var6) {
            if (!this.conf.saslFallback() || var6.getCause() instanceof TimeoutException) {
               throw var6;
            }

            if (LOG.isDebugEnabled()) {
               Throwable cause = (Throwable)(var6.getCause() != null ? var6.getCause() : var6);
               LOG.debug("New auth protocol failed, trying SASL.", cause);
            } else {
               LOG.info("New auth protocol failed, trying SASL.");
            }

            this.doSaslAuth(client, channel);
         }

      }
   }

   private void doSparkAuth(TransportClient client, Channel channel) throws GeneralSecurityException, IOException {
      String secretKey = this.secretKeyHolder.getSecretKey(this.appId);
      AuthEngine engine = new AuthEngine(this.appId, secretKey, this.conf);

      try {
         AuthMessage challenge = engine.challenge();
         ByteBuf challengeData = Unpooled.buffer(challenge.encodedLength());
         challenge.encode(challengeData);
         ByteBuffer responseData = client.sendRpcSync(challengeData.nioBuffer(), (long)this.conf.authRTTimeoutMs());
         AuthMessage response = AuthMessage.decodeMessage(responseData);
         engine.deriveSessionCipher(challenge, response);
         engine.sessionCipher().addToChannel(channel);
      } catch (Throwable var10) {
         try {
            engine.close();
         } catch (Throwable var9) {
            var10.addSuppressed(var9);
         }

         throw var10;
      }

      engine.close();
   }

   private void doSaslAuth(TransportClient client, Channel channel) {
      SaslClientBootstrap sasl = new SaslClientBootstrap(this.conf, this.appId, this.secretKeyHolder);
      sasl.doBootstrap(client, channel);
   }
}
