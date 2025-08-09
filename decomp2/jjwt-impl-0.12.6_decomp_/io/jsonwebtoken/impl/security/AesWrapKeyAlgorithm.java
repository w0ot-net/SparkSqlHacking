package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AesWrapKeyAlgorithm extends AesAlgorithm implements SecretKeyAlgorithm {
   private static final String TRANSFORMATION = "AESWrap";

   public AesWrapKeyAlgorithm(int keyLen) {
      super("A" + keyLen + "KW", "AESWrap", keyLen);
   }

   public KeyResult getEncryptionKey(KeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      final SecretKey kek = this.assertKey((SecretKey)request.getPayload());
      final SecretKey cek = this.generateCek(request);
      byte[] ciphertext = (byte[])this.jca(request).withCipher(new CheckedFunction() {
         public byte[] apply(Cipher cipher) throws Exception {
            cipher.init(3, kek);
            return cipher.wrap(cek);
         }
      });
      return new DefaultKeyResult(cek, ciphertext);
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      final SecretKey kek = this.assertKey((SecretKey)request.getKey());
      final byte[] cekBytes = Assert.notEmpty((byte[])request.getPayload(), "Request content (encrypted key) cannot be null or empty.");
      return (SecretKey)this.jca(request).withCipher(new CheckedFunction() {
         public SecretKey apply(Cipher cipher) throws Exception {
            cipher.init(4, kek);
            Key key = cipher.unwrap(cekBytes, "AES", 3);
            Assert.state(key instanceof SecretKey, "Cipher unwrap must return a SecretKey instance.");
            return (SecretKey)key;
         }
      });
   }
}
