package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.AeadRequest;
import io.jsonwebtoken.security.AeadResult;
import io.jsonwebtoken.security.DecryptAeadRequest;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class GcmAesAeadAlgorithm extends AesAlgorithm implements AeadAlgorithm {
   private static final String TRANSFORMATION_STRING = "AES/GCM/NoPadding";

   public GcmAesAeadAlgorithm(int keyLength) {
      super("A" + keyLength + "GCM", "AES/GCM/NoPadding", keyLength);
   }

   public void encrypt(AeadRequest req, AeadResult res) throws SecurityException {
      Assert.notNull(req, "Request cannot be null.");
      Assert.notNull(res, "Result cannot be null.");
      final SecretKey key = this.assertKey((SecretKey)req.getKey());
      final InputStream plaintext = (InputStream)Assert.notNull(req.getPayload(), "Request content (plaintext) InputStream cannot be null.");
      final OutputStream out = (OutputStream)Assert.notNull(res.getOutputStream(), "Result ciphertext OutputStream cannot be null.");
      final InputStream aad = req.getAssociatedData();
      byte[] iv = this.ensureInitializationVector(req);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      byte[] tag = (byte[])this.jca(req).withCipher(new CheckedFunction() {
         public byte[] apply(Cipher cipher) throws Exception {
            cipher.init(1, key, ivSpec);
            byte[] taggedCiphertext = GcmAesAeadAlgorithm.this.withCipher(cipher, plaintext, aad, out);
            int ciphertextLength = Bytes.length(taggedCiphertext) - 16;
            Streams.write(out, taggedCiphertext, 0, ciphertextLength, "Ciphertext write failure.");
            byte[] tag = new byte[16];
            System.arraycopy(taggedCiphertext, ciphertextLength, tag, 0, 16);
            return tag;
         }
      });
      Streams.flush(out);
      Streams.reset(plaintext);
      res.setTag(tag).setIv(iv);
   }

   public void decrypt(DecryptAeadRequest req, final OutputStream out) throws SecurityException {
      Assert.notNull(req, "Request cannot be null.");
      Assert.notNull(out, "Plaintext OutputStream cannot be null.");
      final SecretKey key = this.assertKey((SecretKey)req.getKey());
      InputStream ciphertext = (InputStream)Assert.notNull(req.getPayload(), "Decryption request content (ciphertext) InputStream cannot be null.");
      final InputStream aad = req.getAssociatedData();
      byte[] tag = Assert.notEmpty(req.getDigest(), "Decryption request authentication tag cannot be null or empty.");
      byte[] iv = this.assertDecryptionIv(req);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      final InputStream taggedCiphertext = new SequenceInputStream(ciphertext, Streams.of(tag));
      this.jca(req).withCipher(new CheckedFunction() {
         public byte[] apply(Cipher cipher) throws Exception {
            cipher.init(2, key, ivSpec);
            byte[] last = GcmAesAeadAlgorithm.this.withCipher(cipher, taggedCiphertext, aad, out);
            Streams.write(out, last, "GcmAesAeadAlgorithm#decrypt plaintext write failure.");
            return Bytes.EMPTY;
         }
      });
      Streams.flush(out);
   }
}
