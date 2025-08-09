package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.io.TeeOutputStream;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.AeadRequest;
import io.jsonwebtoken.security.AeadResult;
import io.jsonwebtoken.security.DecryptAeadRequest;
import io.jsonwebtoken.security.SecretKeyBuilder;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SignatureException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HmacAesAeadAlgorithm extends AesAlgorithm implements AeadAlgorithm {
   private static final String TRANSFORMATION_STRING = "AES/CBC/PKCS5Padding";
   private final DefaultMacAlgorithm SIGALG;

   private static int digestLength(int keyLength) {
      return keyLength * 2;
   }

   private static String id(int keyLength) {
      return "A" + keyLength + "CBC-HS" + digestLength(keyLength);
   }

   public HmacAesAeadAlgorithm(String id, DefaultMacAlgorithm sigAlg) {
      super(id, "AES/CBC/PKCS5Padding", sigAlg.getKeyBitLength());
      this.SIGALG = sigAlg;
   }

   public HmacAesAeadAlgorithm(int keyBitLength) {
      this(id(keyBitLength), new DefaultMacAlgorithm(id(keyBitLength), "HmacSHA" + digestLength(keyBitLength), keyBitLength));
   }

   public int getKeyBitLength() {
      return super.getKeyBitLength() * 2;
   }

   public SecretKeyBuilder key() {
      return new RandomSecretKeyBuilder("AES", this.getKeyBitLength());
   }

   byte[] assertKeyBytes(SecureRequest request) {
      SecretKey key = (SecretKey)Assert.notNull(request.getKey(), "Request key cannot be null.");
      return this.validateLength(key, this.keyBitLength * 2, true);
   }

   public void encrypt(AeadRequest req, AeadResult res) {
      Assert.notNull(req, "Request cannot be null.");
      Assert.notNull(res, "Result cannot be null.");
      byte[] compositeKeyBytes = this.assertKeyBytes(req);
      int halfCount = compositeKeyBytes.length / 2;
      byte[] macKeyBytes = Arrays.copyOfRange(compositeKeyBytes, 0, halfCount);
      byte[] encKeyBytes = Arrays.copyOfRange(compositeKeyBytes, halfCount, compositeKeyBytes.length);

      final SecretKey encryptionKey;
      try {
         encryptionKey = new SecretKeySpec(encKeyBytes, "AES");
      } finally {
         Bytes.clear(encKeyBytes);
         Bytes.clear(compositeKeyBytes);
      }

      final InputStream plaintext = (InputStream)Assert.notNull(req.getPayload(), "Request content (plaintext) InputStream cannot be null.");
      OutputStream out = (OutputStream)Assert.notNull(res.getOutputStream(), "Result ciphertext OutputStream cannot be null.");
      InputStream aad = req.getAssociatedData();
      byte[] iv = this.ensureInitializationVector(req);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      ByteArrayOutputStream copy = new ByteArrayOutputStream(8192);
      final TeeOutputStream tee = new TeeOutputStream(out, copy);
      this.jca(req).withCipher(new CheckedFunction() {
         public Object apply(Cipher cipher) throws Exception {
            cipher.init(1, encryptionKey, ivSpec);
            HmacAesAeadAlgorithm.this.withCipher(cipher, plaintext, tee);
            return null;
         }
      });
      byte[] aadBytes = aad == null ? Bytes.EMPTY : Streams.bytes(aad, "Unable to read AAD bytes.");

      try {
         byte[] tag = this.sign(aadBytes, iv, Streams.of(copy.toByteArray()), macKeyBytes);
         res.setTag(tag).setIv(iv);
      } finally {
         Bytes.clear(macKeyBytes);
      }

   }

   private byte[] sign(byte[] aad, byte[] iv, InputStream ciphertext, byte[] macKeyBytes) {
      long aadLength = (long)io.jsonwebtoken.lang.Arrays.length(aad);
      long aadLengthInBits = aadLength * 8L;
      long aadLengthInBitsAsUnsignedInt = aadLengthInBits & 4294967295L;
      byte[] AL = Bytes.toBytes(aadLengthInBitsAsUnsignedInt);
      Collection<InputStream> streams = new ArrayList(4);
      if (!Bytes.isEmpty(aad)) {
         streams.add(Streams.of(aad));
      }

      streams.add(Streams.of(iv));
      streams.add(ciphertext);
      streams.add(Streams.of(AL));
      InputStream in = new SequenceInputStream(Collections.enumeration(streams));
      SecretKey key = new SecretKeySpec(macKeyBytes, this.SIGALG.getJcaName());
      SecureRequest<InputStream, SecretKey> request = new DefaultSecureRequest(in, (Provider)null, (SecureRandom)null, key);
      byte[] digest = this.SIGALG.digest(request);
      return this.assertTag(Arrays.copyOfRange(digest, 0, macKeyBytes.length));
   }

   public void decrypt(DecryptAeadRequest req, final OutputStream plaintext) {
      Assert.notNull(req, "Request cannot be null.");
      Assert.notNull(plaintext, "Plaintext OutputStream cannot be null.");
      byte[] compositeKeyBytes = this.assertKeyBytes(req);
      int halfCount = compositeKeyBytes.length / 2;
      byte[] macKeyBytes = Arrays.copyOfRange(compositeKeyBytes, 0, halfCount);
      byte[] encKeyBytes = Arrays.copyOfRange(compositeKeyBytes, halfCount, compositeKeyBytes.length);

      final SecretKey decryptionKey;
      try {
         decryptionKey = new SecretKeySpec(encKeyBytes, "AES");
      } finally {
         Bytes.clear(encKeyBytes);
         Bytes.clear(compositeKeyBytes);
      }

      final InputStream in = (InputStream)Assert.notNull(req.getPayload(), "Decryption request content (ciphertext) InputStream cannot be null.");
      InputStream aad = req.getAssociatedData();
      byte[] tag = this.assertTag(req.getDigest());
      byte[] iv = this.assertDecryptionIv(req);
      final AlgorithmParameterSpec ivSpec = this.getIvSpec(iv);
      byte[] aadBytes = aad == null ? Bytes.EMPTY : Streams.bytes(aad, "Unable to read AAD bytes.");

      byte[] digest;
      try {
         digest = this.sign(aadBytes, iv, in, macKeyBytes);
      } finally {
         Bytes.clear(macKeyBytes);
      }

      if (!MessageDigest.isEqual(digest, tag)) {
         String msg = "Ciphertext decryption failed: Authentication tag verification failed.";
         throw new SignatureException(msg);
      } else {
         Streams.reset(in);
         this.jca(req).withCipher(new CheckedFunction() {
            public byte[] apply(Cipher cipher) throws Exception {
               cipher.init(2, decryptionKey, ivSpec);
               HmacAesAeadAlgorithm.this.withCipher(cipher, in, plaintext);
               return Bytes.EMPTY;
            }
         });
      }
   }
}
