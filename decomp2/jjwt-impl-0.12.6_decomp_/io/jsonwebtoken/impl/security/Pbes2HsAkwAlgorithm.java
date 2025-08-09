package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.DefaultJweHeader;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.SecurityException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Pbes2HsAkwAlgorithm extends CryptoAlgorithm implements KeyAlgorithm {
   private static final int DEFAULT_SHA256_ITERATIONS = 310000;
   private static final int DEFAULT_SHA384_ITERATIONS = 210000;
   private static final int DEFAULT_SHA512_ITERATIONS = 120000;
   private static final int MIN_RECOMMENDED_ITERATIONS = 1000;
   private static final String MIN_ITERATIONS_MSG_PREFIX = "[JWA RFC 7518, Section 4.8.1.2](https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.2) recommends password-based-encryption iterations be greater than or equal to 1000. Provided: ";
   private static final double MAX_ITERATIONS_FACTOR = (double)2.5F;
   private final int HASH_BYTE_LENGTH;
   private final int DERIVED_KEY_BIT_LENGTH;
   private final byte[] SALT_PREFIX;
   private final int DEFAULT_ITERATIONS;
   private final int MAX_ITERATIONS;
   private final KeyAlgorithm wrapAlg;

   private static byte[] toRfcSaltPrefix(byte[] bytes) {
      byte[] output = new byte[bytes.length + 1];
      System.arraycopy(bytes, 0, output, 0, bytes.length);
      return output;
   }

   private static int hashBitLength(int keyBitLength) {
      return keyBitLength * 2;
   }

   private static String idFor(int hashBitLength, KeyAlgorithm wrapAlg) {
      Assert.notNull(wrapAlg, "wrapAlg argument cannot be null.");
      return "PBES2-HS" + hashBitLength + "+" + wrapAlg.getId();
   }

   public static int assertIterations(int iterations) {
      if (iterations < 1000) {
         String msg = "[JWA RFC 7518, Section 4.8.1.2](https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.2) recommends password-based-encryption iterations be greater than or equal to 1000. Provided: " + iterations;
         throw new IllegalArgumentException(msg);
      } else {
         return iterations;
      }
   }

   public Pbes2HsAkwAlgorithm(int keyBitLength) {
      this(hashBitLength(keyBitLength), new AesWrapKeyAlgorithm(keyBitLength));
   }

   protected Pbes2HsAkwAlgorithm(int hashBitLength, KeyAlgorithm wrapAlg) {
      super(idFor(hashBitLength, wrapAlg), "PBKDF2WithHmacSHA" + hashBitLength);
      this.wrapAlg = wrapAlg;
      this.HASH_BYTE_LENGTH = hashBitLength / 8;
      if (hashBitLength >= 512) {
         this.DEFAULT_ITERATIONS = 120000;
      } else if (hashBitLength >= 384) {
         this.DEFAULT_ITERATIONS = 210000;
      } else {
         this.DEFAULT_ITERATIONS = 310000;
      }

      this.MAX_ITERATIONS = (int)((double)this.DEFAULT_ITERATIONS * (double)2.5F);
      this.DERIVED_KEY_BIT_LENGTH = hashBitLength / 2;
      this.SALT_PREFIX = toRfcSaltPrefix(this.getId().getBytes(StandardCharsets.UTF_8));
   }

   protected SecretKey deriveKey(SecretKeyFactory factory, char[] password, byte[] rfcSalt, int iterations) throws Exception {
      PBEKeySpec spec = new PBEKeySpec(password, rfcSalt, iterations, this.DERIVED_KEY_BIT_LENGTH);

      SecretKeySpec var7;
      try {
         SecretKey derived = factory.generateSecret(spec);
         var7 = new SecretKeySpec(derived.getEncoded(), "AES");
      } finally {
         spec.clearPassword();
      }

      return var7;
   }

   private SecretKey deriveKey(KeyRequest request, final char[] password, final byte[] salt, final int iterations) {
      SecretKey var5;
      try {
         Assert.notEmpty(password, "Key password character array cannot be null or empty.");
         var5 = (SecretKey)this.jca(request).withSecretKeyFactory(new CheckedFunction() {
            public SecretKey apply(SecretKeyFactory factory) throws Exception {
               return Pbes2HsAkwAlgorithm.this.deriveKey(factory, password, salt, iterations);
            }
         });
      } finally {
         Arrays.fill(password, '\u0000');
      }

      return var5;
   }

   protected byte[] generateInputSalt(KeyRequest request) {
      byte[] inputSalt = new byte[this.HASH_BYTE_LENGTH];
      ensureSecureRandom(request).nextBytes(inputSalt);
      return inputSalt;
   }

   protected byte[] toRfcSalt(byte[] inputSalt) {
      return Bytes.concat(this.SALT_PREFIX, inputSalt);
   }

   public KeyResult getEncryptionKey(KeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      Password key = (Password)Assert.notNull(request.getPayload(), "Encryption Password cannot be null.");
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "JweHeader cannot be null.");
      Integer p2c = header.getPbes2Count();
      if (p2c == null) {
         p2c = this.DEFAULT_ITERATIONS;
         header.put(DefaultJweHeader.P2C.getId(), p2c);
      }

      int iterations = assertIterations(p2c);
      byte[] inputSalt = this.generateInputSalt(request);
      byte[] rfcSalt = this.toRfcSalt(inputSalt);
      char[] password = key.toCharArray();
      SecretKey derivedKek = this.deriveKey(request, password, rfcSalt, iterations);
      KeyRequest<SecretKey> wrapReq = new DefaultKeyRequest(derivedKek, request.getProvider(), request.getSecureRandom(), request.getHeader(), request.getEncryptionAlgorithm());
      KeyResult result = this.wrapAlg.getEncryptionKey(wrapReq);
      request.getHeader().put(DefaultJweHeader.P2S.getId(), inputSalt);
      return result;
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "Request JweHeader cannot be null.");
      Password key = (Password)Assert.notNull(request.getKey(), "Decryption Password cannot be null.");
      ParameterReadable reader = new RequiredParameterReader(header);
      byte[] inputSalt = (byte[])reader.get(DefaultJweHeader.P2S);
      Parameter<Integer> param = DefaultJweHeader.P2C;
      int iterations = (Integer)reader.get(param);
      if (iterations > this.MAX_ITERATIONS) {
         String msg = "JWE Header " + param + " value " + iterations + " exceeds " + this.getId() + " maximum " + "allowed value " + this.MAX_ITERATIONS + ". The larger value is rejected to help mitigate " + "potential Denial of Service attacks.";
         throw new UnsupportedJwtException(msg);
      } else {
         byte[] rfcSalt = Bytes.concat(this.SALT_PREFIX, inputSalt);
         char[] password = key.toCharArray();
         SecretKey derivedKek = this.deriveKey((KeyRequest)request, password, rfcSalt, iterations);
         DecryptionKeyRequest<SecretKey> unwrapReq = new DefaultDecryptionKeyRequest((byte[])request.getPayload(), request.getProvider(), request.getSecureRandom(), header, request.getEncryptionAlgorithm(), derivedKek);
         return this.wrapAlg.getDecryptionKey(unwrapReq);
      }
   }
}
