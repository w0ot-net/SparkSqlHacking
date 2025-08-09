package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.WeakKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DefaultRsaKeyAlgorithm extends CryptoAlgorithm implements KeyAlgorithm {
   private final AlgorithmParameterSpec SPEC;
   private static final int MIN_KEY_BIT_LENGTH = 2048;

   public DefaultRsaKeyAlgorithm(String id, String jcaTransformationString) {
      this(id, jcaTransformationString, (AlgorithmParameterSpec)null);
   }

   public DefaultRsaKeyAlgorithm(String id, String jcaTransformationString, AlgorithmParameterSpec spec) {
      super(id, jcaTransformationString);
      this.SPEC = spec;
   }

   private static String keyType(boolean encryption) {
      return encryption ? "encryption" : "decryption";
   }

   protected void validate(Key key, boolean encryption) {
      if (!RsaSignatureAlgorithm.isRsaAlgorithmName(key)) {
         throw new InvalidKeyException("Invalid RSA key algorithm name.");
      } else if (RsaSignatureAlgorithm.isPss(key)) {
         String msg = "RSASSA-PSS keys may not be used for " + keyType(encryption) + ", only digital signature algorithms.";
         throw new InvalidKeyException(msg);
      } else {
         int size = KeysBridge.findBitLength(key);
         if (size >= 0) {
            if (size < 2048) {
               String id = this.getId();
               String section = id.startsWith("RSA1") ? "4.2" : "4.3";
               String msg = "The RSA " + keyType(encryption) + " key size (aka modulus bit length) is " + size + " bits which is not secure enough for the " + id + " algorithm. " + "The JWT JWA Specification (RFC 7518, Section " + section + ") states that RSA keys MUST " + "have a size >= " + 2048 + " bits. See " + "https://www.rfc-editor.org/rfc/rfc7518.html#section-" + section + " for more information.";
               throw new WeakKeyException(msg);
            }
         }
      }
   }

   public KeyResult getEncryptionKey(final KeyRequest request) throws SecurityException {
      Assert.notNull(request, "Request cannot be null.");
      final PublicKey kek = (PublicKey)Assert.notNull(request.getPayload(), "RSA PublicKey encryption key cannot be null.");
      this.validate(kek, true);
      final SecretKey cek = this.generateCek(request);
      byte[] ciphertext = (byte[])this.jca(request).withCipher(new CheckedFunction() {
         public byte[] apply(Cipher cipher) throws Exception {
            if (DefaultRsaKeyAlgorithm.this.SPEC == null) {
               cipher.init(3, kek, CryptoAlgorithm.ensureSecureRandom(request));
            } else {
               cipher.init(3, kek, DefaultRsaKeyAlgorithm.this.SPEC, CryptoAlgorithm.ensureSecureRandom(request));
            }

            return cipher.wrap(cek);
         }
      });
      return new DefaultKeyResult(cek, ciphertext);
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      final PrivateKey kek = (PrivateKey)Assert.notNull(request.getKey(), "RSA PrivateKey decryption key cannot be null.");
      this.validate(kek, false);
      final byte[] cekBytes = Assert.notEmpty((byte[])request.getPayload(), "Request content (encrypted key) cannot be null or empty.");
      return (SecretKey)this.jca(request).withCipher(new CheckedFunction() {
         public SecretKey apply(Cipher cipher) throws Exception {
            if (DefaultRsaKeyAlgorithm.this.SPEC == null) {
               cipher.init(4, kek);
            } else {
               cipher.init(4, kek, DefaultRsaKeyAlgorithm.this.SPEC);
            }

            Key key = cipher.unwrap(cekBytes, "AES", 3);
            return (SecretKey)Assert.isInstanceOf(SecretKey.class, key, "Cipher unwrap must return a SecretKey instance.");
         }
      });
   }
}
