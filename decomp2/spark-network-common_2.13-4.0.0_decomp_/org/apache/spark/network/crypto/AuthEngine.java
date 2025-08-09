package org.apache.spark.network.crypto;

import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Hkdf;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.X25519;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Bytes;

class AuthEngine implements Closeable {
   public static final byte[] DERIVED_KEY_INFO;
   public static final byte[] INPUT_IV_INFO;
   public static final byte[] OUTPUT_IV_INFO;
   private static final String MAC_ALGORITHM = "HMACSHA256";
   private static final String LEGACY_CIPHER_ALGORITHM = "AES/CTR/NoPadding";
   private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
   private static final int AES_GCM_KEY_SIZE_BYTES = 16;
   private static final byte[] EMPTY_TRANSCRIPT;
   private static final int UNSAFE_SKIP_HKDF_VERSION = 1;
   private final String appId;
   private final byte[] preSharedSecret;
   private final TransportConf conf;
   private final Properties cryptoConf;
   private final boolean unsafeSkipFinalHkdf;
   private byte[] clientPrivateKey;
   private TransportCipher sessionCipher;

   AuthEngine(String appId, String preSharedSecret, TransportConf conf) {
      Preconditions.checkNotNull(appId);
      Preconditions.checkNotNull(preSharedSecret);
      this.appId = appId;
      this.preSharedSecret = preSharedSecret.getBytes(StandardCharsets.UTF_8);
      this.conf = conf;
      this.cryptoConf = conf.cryptoConf();
      this.unsafeSkipFinalHkdf = conf.authEngineVersion() == 1;
   }

   @VisibleForTesting
   void setClientPrivateKey(byte[] privateKey) {
      this.clientPrivateKey = privateKey;
   }

   private AuthMessage encryptEphemeralPublicKey(byte[] ephemeralX25519PublicKey, byte[] transcript) throws GeneralSecurityException {
      byte[] nonSecretSalt = Random.randBytes(16);
      byte[] aadState = Bytes.concat(this.appId.getBytes(StandardCharsets.UTF_8), nonSecretSalt, transcript);
      byte[] derivedKeyEncryptingKey = Hkdf.computeHkdf("HMACSHA256", this.preSharedSecret, nonSecretSalt, aadState, 16);
      byte[] aesGcmCiphertext = (new AesGcmJce(derivedKeyEncryptingKey)).encrypt(ephemeralX25519PublicKey, aadState);
      return new AuthMessage(this.appId, nonSecretSalt, aesGcmCiphertext);
   }

   private byte[] decryptEphemeralPublicKey(AuthMessage encryptedPublicKey, byte[] transcript) throws GeneralSecurityException {
      Preconditions.checkArgument(this.appId.equals(encryptedPublicKey.appId()));
      byte[] aadState = Bytes.concat(this.appId.getBytes(StandardCharsets.UTF_8), encryptedPublicKey.salt(), transcript);
      byte[] derivedKeyEncryptingKey = Hkdf.computeHkdf("HMACSHA256", this.preSharedSecret, encryptedPublicKey.salt(), aadState, 16);
      return (new AesGcmJce(derivedKeyEncryptingKey)).decrypt(encryptedPublicKey.ciphertext(), aadState);
   }

   AuthMessage challenge() throws GeneralSecurityException {
      this.setClientPrivateKey(X25519.generatePrivateKey());
      return this.encryptEphemeralPublicKey(X25519.publicFromPrivate(this.clientPrivateKey), EMPTY_TRANSCRIPT);
   }

   AuthMessage response(AuthMessage encryptedClientPublicKey) throws GeneralSecurityException {
      Preconditions.checkArgument(this.appId.equals(encryptedClientPublicKey.appId()));
      byte[] clientPublicKey = this.decryptEphemeralPublicKey(encryptedClientPublicKey, EMPTY_TRANSCRIPT);
      byte[] serverEphemeralPrivateKey = X25519.generatePrivateKey();
      AuthMessage ephemeralServerPublicKey = this.encryptEphemeralPublicKey(X25519.publicFromPrivate(serverEphemeralPrivateKey), this.getTranscript(encryptedClientPublicKey));
      byte[] sharedSecret = X25519.computeSharedSecret(serverEphemeralPrivateKey, clientPublicKey);
      byte[] challengeResponseTranscript = this.getTranscript(encryptedClientPublicKey, ephemeralServerPublicKey);
      this.sessionCipher = this.generateTransportCipher(sharedSecret, false, challengeResponseTranscript);
      return ephemeralServerPublicKey;
   }

   void deriveSessionCipher(AuthMessage encryptedClientPublicKey, AuthMessage encryptedServerPublicKey) throws GeneralSecurityException {
      Preconditions.checkArgument(this.appId.equals(encryptedClientPublicKey.appId()));
      Preconditions.checkArgument(this.appId.equals(encryptedServerPublicKey.appId()));
      byte[] serverPublicKey = this.decryptEphemeralPublicKey(encryptedServerPublicKey, this.getTranscript(encryptedClientPublicKey));
      byte[] sharedSecret = X25519.computeSharedSecret(this.clientPrivateKey, serverPublicKey);
      byte[] challengeResponseTranscript = this.getTranscript(encryptedClientPublicKey, encryptedServerPublicKey);
      this.sessionCipher = this.generateTransportCipher(sharedSecret, true, challengeResponseTranscript);
   }

   private TransportCipher generateTransportCipher(byte[] sharedSecret, boolean isClient, byte[] transcript) throws GeneralSecurityException {
      byte[] derivedKey = this.unsafeSkipFinalHkdf ? sharedSecret : Hkdf.computeHkdf("HMACSHA256", sharedSecret, transcript, DERIVED_KEY_INFO, 16);
      byte[] clientIv = Hkdf.computeHkdf("HMACSHA256", sharedSecret, transcript, INPUT_IV_INFO, 16);
      byte[] serverIv = Hkdf.computeHkdf("HMACSHA256", sharedSecret, transcript, OUTPUT_IV_INFO, 16);
      SecretKeySpec sessionKey = new SecretKeySpec(derivedKey, "AES");
      if ("AES/CTR/NoPadding".equalsIgnoreCase(this.conf.cipherTransformation())) {
         return new CtrTransportCipher(this.cryptoConf, sessionKey, isClient ? clientIv : serverIv, isClient ? serverIv : clientIv);
      } else if ("AES/GCM/NoPadding".equalsIgnoreCase(this.conf.cipherTransformation())) {
         return new GcmTransportCipher(sessionKey);
      } else {
         throw new IllegalArgumentException(String.format("Unsupported cipher mode: %s. %s and %s are supported.", this.conf.cipherTransformation(), "AES/GCM/NoPadding", "AES/CTR/NoPadding"));
      }
   }

   private byte[] getTranscript(AuthMessage... encryptedPublicKeys) {
      ByteBuf transcript = Unpooled.buffer(Arrays.stream(encryptedPublicKeys).mapToInt((k) -> k.encodedLength()).sum());
      Arrays.stream(encryptedPublicKeys).forEachOrdered((k) -> k.encode(transcript));
      return transcript.array();
   }

   TransportCipher sessionCipher() {
      Preconditions.checkState(this.sessionCipher != null);
      return this.sessionCipher;
   }

   public void close() {
   }

   static {
      DERIVED_KEY_INFO = "derivedKey".getBytes(StandardCharsets.UTF_8);
      INPUT_IV_INFO = "inputIv".getBytes(StandardCharsets.UTF_8);
      OUTPUT_IV_INFO = "outputIv".getBytes(StandardCharsets.UTF_8);
      EMPTY_TRANSCRIPT = new byte[0];
   }
}
