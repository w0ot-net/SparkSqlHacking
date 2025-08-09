package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.impl.DefaultJweHeader;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AesGcmKeyAlgorithm extends AesAlgorithm implements SecretKeyAlgorithm {
   public static final String TRANSFORMATION = "AES/GCM/NoPadding";

   public AesGcmKeyAlgorithm(int keyLen) {
      super("A" + keyLen + "GCMKW", "AES/GCM/NoPadding", keyLen);
   }

   public KeyResult getEncryptionKey(KeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "Request JweHeader cannot be null.");
      final SecretKey kek = this.assertKey((SecretKey)request.getPayload());
      final SecretKey cek = this.generateCek(request);
      byte[] iv = this.ensureInitializationVector(request);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      byte[] taggedCiphertext = (byte[])this.jca(request).withCipher(new CheckedFunction() {
         public byte[] apply(Cipher cipher) throws Exception {
            cipher.init(3, kek, ivSpec);
            return cipher.wrap(cek);
         }
      });
      int tagByteLength = this.tagBitLength / 8;
      int ciphertextLength = taggedCiphertext.length - tagByteLength;
      byte[] ciphertext = new byte[ciphertextLength];
      System.arraycopy(taggedCiphertext, 0, ciphertext, 0, ciphertextLength);
      byte[] tag = new byte[tagByteLength];
      System.arraycopy(taggedCiphertext, ciphertextLength, tag, 0, tagByteLength);
      String encodedIv = (String)Encoders.BASE64URL.encode(iv);
      String encodedTag = (String)Encoders.BASE64URL.encode(tag);
      header.put(DefaultJweHeader.IV.getId(), encodedIv);
      header.put(DefaultJweHeader.TAG.getId(), encodedTag);
      return new DefaultKeyResult(cek, ciphertext);
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      final SecretKey kek = this.assertKey((SecretKey)request.getKey());
      byte[] cekBytes = Assert.notEmpty((byte[])request.getPayload(), "Decryption request content (ciphertext) cannot be null or empty.");
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "Request JweHeader cannot be null.");
      ParameterReadable frHeader = (ParameterReadable)Assert.isInstanceOf(ParameterReadable.class, header, "Header must implement ParameterReadable.");
      ParameterReadable reader = new RequiredParameterReader(frHeader);
      byte[] tag = (byte[])reader.get(DefaultJweHeader.TAG);
      byte[] iv = (byte[])reader.get(DefaultJweHeader.IV);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      final byte[] taggedCiphertext = Bytes.concat(cekBytes, tag);
      return (SecretKey)this.jca(request).withCipher(new CheckedFunction() {
         public SecretKey apply(Cipher cipher) throws Exception {
            cipher.init(4, kek, ivSpec);
            Key key = cipher.unwrap(taggedCiphertext, "AES", 3);
            Assert.state(key instanceof SecretKey, "cipher.unwrap must produce a SecretKey instance.");
            return (SecretKey)key;
         }
      });
   }
}
