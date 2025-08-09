package org.apache.spark.network.sasl;

import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.RealmChoiceCallback;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.sparkproject.guava.collect.ImmutableMap;

public class SparkSaslClient implements SaslEncryptionBackend {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SparkSaslClient.class);
   private final String secretKeyId;
   private final SecretKeyHolder secretKeyHolder;
   private final String expectedQop;
   private SaslClient saslClient;

   public SparkSaslClient(String secretKeyId, SecretKeyHolder secretKeyHolder, boolean encrypt) {
      this.secretKeyId = secretKeyId;
      this.secretKeyHolder = secretKeyHolder;
      this.expectedQop = encrypt ? "auth-conf" : "auth";
      Map<String, String> saslProps = ImmutableMap.builder().put("javax.security.sasl.qop", this.expectedQop).build();

      try {
         this.saslClient = Sasl.createSaslClient(new String[]{"DIGEST-MD5"}, (String)null, (String)null, "default", saslProps, new ClientCallbackHandler());
      } catch (SaslException e) {
         throw new RuntimeException(e);
      }
   }

   public synchronized byte[] firstToken() {
      if (this.saslClient != null && this.saslClient.hasInitialResponse()) {
         try {
            return this.saslClient.evaluateChallenge(new byte[0]);
         } catch (SaslException e) {
            throw new RuntimeException(e);
         }
      } else {
         return new byte[0];
      }
   }

   public synchronized boolean isComplete() {
      return this.saslClient != null && this.saslClient.isComplete();
   }

   public Object getNegotiatedProperty(String name) {
      return this.saslClient.getNegotiatedProperty(name);
   }

   public synchronized byte[] response(byte[] token) {
      try {
         return this.saslClient != null ? this.saslClient.evaluateChallenge(token) : new byte[0];
      } catch (SaslException e) {
         throw new RuntimeException(e);
      }
   }

   public synchronized void dispose() {
      if (this.saslClient != null) {
         try {
            this.saslClient.dispose();
         } catch (SaslException var5) {
         } finally {
            this.saslClient = null;
         }
      }

   }

   public byte[] wrap(byte[] data, int offset, int len) throws SaslException {
      return this.saslClient.wrap(data, offset, len);
   }

   public byte[] unwrap(byte[] data, int offset, int len) throws SaslException {
      return this.saslClient.unwrap(data, offset, len);
   }

   private class ClientCallbackHandler implements CallbackHandler {
      public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
         for(Callback callback : callbacks) {
            if (callback instanceof NameCallback nc) {
               SparkSaslClient.logger.trace("SASL client callback: setting username");
               nc.setName(SparkSaslServer.encodeIdentifier(SparkSaslClient.this.secretKeyHolder.getSaslUser(SparkSaslClient.this.secretKeyId)));
            } else if (callback instanceof PasswordCallback pc) {
               SparkSaslClient.logger.trace("SASL client callback: setting password");
               pc.setPassword(SparkSaslServer.encodePassword(SparkSaslClient.this.secretKeyHolder.getSecretKey(SparkSaslClient.this.secretKeyId)));
            } else if (callback instanceof RealmCallback rc) {
               SparkSaslClient.logger.trace("SASL client callback: setting realm");
               rc.setText(rc.getDefaultText());
            } else if (!(callback instanceof RealmChoiceCallback)) {
               throw new UnsupportedCallbackException(callback, "Unrecognized SASL DIGEST-MD5 Callback");
            }
         }

      }
   }
}
