package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

public final class RsaKemHybridDecrypt implements HybridDecrypt {
   private final RSAPrivateKey recipientPrivateKey;
   private final String hkdfHmacAlgo;
   private final byte[] hkdfSalt;
   private final AeadFactory aeadFactory;

   public RsaKemHybridDecrypt(final RSAPrivateKey recipientPrivateKey, String hkdfHmacAlgo, final byte[] hkdfSalt, AeadFactory aeadFactory) throws GeneralSecurityException {
      RsaKem.validateRsaModulus(recipientPrivateKey.getModulus());
      this.recipientPrivateKey = recipientPrivateKey;
      this.hkdfSalt = hkdfSalt;
      this.hkdfHmacAlgo = hkdfHmacAlgo;
      this.aeadFactory = aeadFactory;
   }

   public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
      int modSizeInBytes = RsaKem.bigIntSizeInBytes(this.recipientPrivateKey.getModulus());
      if (ciphertext.length < modSizeInBytes) {
         throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", modSizeInBytes, ciphertext.length));
      } else {
         ByteBuffer cipherBuffer = ByteBuffer.wrap(ciphertext);
         byte[] token = new byte[modSizeInBytes];
         cipherBuffer.get(token);
         Cipher rsaCipher = Cipher.getInstance("RSA/ECB/NoPadding");
         rsaCipher.init(2, this.recipientPrivateKey);
         byte[] sharedSecret = rsaCipher.doFinal(token);
         byte[] demKey = Hkdf.computeHkdf(this.hkdfHmacAlgo, sharedSecret, this.hkdfSalt, contextInfo, this.aeadFactory.getKeySizeInBytes());
         Aead aead = this.aeadFactory.createAead(demKey);
         byte[] demPayload = new byte[cipherBuffer.remaining()];
         cipherBuffer.get(demPayload);
         return aead.decrypt(demPayload, RsaKem.EMPTY_AAD);
      }
   }
}
