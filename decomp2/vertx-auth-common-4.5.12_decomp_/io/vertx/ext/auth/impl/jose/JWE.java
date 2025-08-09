package io.vertx.ext.auth.impl.jose;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class JWE {
   private final Cipher cipher;
   private final JWK jwk;

   public JWE(JWK jwk) {
      if (jwk.use() != null && !"enc".equals(jwk.use())) {
         try {
            this.cipher = Cipher.getInstance(jwk.kty());
         } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
         }

         this.jwk = jwk;
      } else {
         throw new IllegalArgumentException("JWK isn't meant to perform JWE operations");
      }
   }

   public byte[] encrypt(byte[] payload) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
      PublicKey publicKey = this.jwk.publicKey();
      if (publicKey == null) {
         throw new IllegalStateException("Key doesn't contain a pubKey material");
      } else {
         synchronized(this.cipher) {
            this.cipher.init(1, publicKey);
            this.cipher.update(payload);
            return this.cipher.doFinal();
         }
      }
   }

   public byte[] decrypt(byte[] payload) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
      PrivateKey privateKey = this.jwk.privateKey();
      if (privateKey == null) {
         throw new IllegalStateException("Key doesn't contain a secKey material");
      } else {
         synchronized(this.cipher) {
            this.cipher.init(2, privateKey);
            this.cipher.update(payload);
            return this.cipher.doFinal();
         }
      }
   }

   public String label() {
      return this.jwk.label();
   }
}
