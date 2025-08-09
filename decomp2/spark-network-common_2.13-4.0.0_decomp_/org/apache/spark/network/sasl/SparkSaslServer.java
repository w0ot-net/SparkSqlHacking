package org.apache.spark.network.sasl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableMap;

public class SparkSaslServer implements SaslEncryptionBackend {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SparkSaslServer.class);
   static final String DEFAULT_REALM = "default";
   static final String DIGEST = "DIGEST-MD5";
   static final String QOP_AUTH_CONF = "auth-conf";
   static final String QOP_AUTH = "auth";
   private final String secretKeyId;
   private final SecretKeyHolder secretKeyHolder;
   private SaslServer saslServer;

   public SparkSaslServer(String secretKeyId, SecretKeyHolder secretKeyHolder, boolean alwaysEncrypt) {
      this.secretKeyId = secretKeyId;
      this.secretKeyHolder = secretKeyHolder;
      String qop = alwaysEncrypt ? "auth-conf" : String.format("%s,%s", "auth-conf", "auth");
      Map<String, String> saslProps = ImmutableMap.builder().put("javax.security.sasl.server.authentication", "true").put("javax.security.sasl.qop", qop).build();

      try {
         this.saslServer = Sasl.createSaslServer("DIGEST-MD5", (String)null, "default", saslProps, new DigestCallbackHandler());
      } catch (SaslException e) {
         throw new RuntimeException(e);
      }
   }

   public synchronized boolean isComplete() {
      return this.saslServer != null && this.saslServer.isComplete();
   }

   public Object getNegotiatedProperty(String name) {
      return this.saslServer.getNegotiatedProperty(name);
   }

   public synchronized byte[] response(byte[] token) {
      try {
         return this.saslServer != null ? this.saslServer.evaluateResponse(token) : new byte[0];
      } catch (SaslException e) {
         throw new RuntimeException(e);
      }
   }

   public synchronized void dispose() {
      if (this.saslServer != null) {
         try {
            this.saslServer.dispose();
         } catch (SaslException var5) {
         } finally {
            this.saslServer = null;
         }
      }

   }

   public byte[] wrap(byte[] data, int offset, int len) throws SaslException {
      return this.saslServer.wrap(data, offset, len);
   }

   public byte[] unwrap(byte[] data, int offset, int len) throws SaslException {
      return this.saslServer.unwrap(data, offset, len);
   }

   public static String encodeIdentifier(String identifier) {
      Preconditions.checkNotNull(identifier, "User cannot be null if SASL is enabled");
      return getBase64EncodedString(identifier);
   }

   public static char[] encodePassword(String password) {
      Preconditions.checkNotNull(password, "Password cannot be null if SASL is enabled");
      return getBase64EncodedString(password).toCharArray();
   }

   private static String getBase64EncodedString(String str) {
      ByteBuf byteBuf = null;
      ByteBuf encodedByteBuf = null;

      String var3;
      try {
         byteBuf = Unpooled.wrappedBuffer(str.getBytes(StandardCharsets.UTF_8));
         encodedByteBuf = Base64.encode(byteBuf);
         var3 = encodedByteBuf.toString(StandardCharsets.UTF_8);
      } finally {
         if (byteBuf != null) {
            byteBuf.release();
            if (encodedByteBuf != null) {
               encodedByteBuf.release();
            }
         }

      }

      return var3;
   }

   private class DigestCallbackHandler implements CallbackHandler {
      public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
         for(Callback callback : callbacks) {
            if (callback instanceof NameCallback nc) {
               SparkSaslServer.logger.trace("SASL server callback: setting username");
               nc.setName(SparkSaslServer.encodeIdentifier(SparkSaslServer.this.secretKeyHolder.getSaslUser(SparkSaslServer.this.secretKeyId)));
            } else if (callback instanceof PasswordCallback pc) {
               SparkSaslServer.logger.trace("SASL server callback: setting password");
               pc.setPassword(SparkSaslServer.encodePassword(SparkSaslServer.this.secretKeyHolder.getSecretKey(SparkSaslServer.this.secretKeyId)));
            } else if (callback instanceof RealmCallback rc) {
               SparkSaslServer.logger.trace("SASL server callback: setting realm");
               rc.setText(rc.getDefaultText());
            } else {
               if (!(callback instanceof AuthorizeCallback)) {
                  throw new UnsupportedCallbackException(callback, "Unrecognized SASL DIGEST-MD5 Callback");
               }

               AuthorizeCallback ac = (AuthorizeCallback)callback;
               String authId = ac.getAuthenticationID();
               String authzId = ac.getAuthorizationID();
               ac.setAuthorized(authId.equals(authzId));
               if (ac.isAuthorized()) {
                  ac.setAuthorizedID(authzId);
               }

               SparkSaslServer.logger.debug("SASL Authorization complete, authorized set to {}", ac.isAuthorized());
            }
         }

      }
   }
}
