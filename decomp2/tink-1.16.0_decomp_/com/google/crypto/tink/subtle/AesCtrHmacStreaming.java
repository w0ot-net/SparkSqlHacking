package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKey;
import com.google.crypto.tink.streamingaead.AesCtrHmacStreamingParameters;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@AccessesPartialKey
public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final int NONCE_SIZE_IN_BYTES = 16;
   private static final int NONCE_PREFIX_IN_BYTES = 7;
   private static final int HMAC_KEY_SIZE_IN_BYTES = 32;
   private final int keySizeInBytes;
   private final String tagAlgo;
   private final int tagSizeInBytes;
   private final int ciphertextSegmentSize;
   private final int plaintextSegmentSize;
   private final int firstSegmentOffset;
   private final String hkdfAlgo;
   private final byte[] ikm;

   public AesCtrHmacStreaming(byte[] ikm, String hkdfAlgo, int keySizeInBytes, String tagAlgo, int tagSizeInBytes, int ciphertextSegmentSize, int firstSegmentOffset) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-CTR-HMAC streaming in FIPS-mode.");
      } else {
         validateParameters(ikm.length, keySizeInBytes, tagAlgo, tagSizeInBytes, ciphertextSegmentSize, firstSegmentOffset);
         this.ikm = Arrays.copyOf(ikm, ikm.length);
         this.hkdfAlgo = hkdfAlgo;
         this.keySizeInBytes = keySizeInBytes;
         this.tagAlgo = tagAlgo;
         this.tagSizeInBytes = tagSizeInBytes;
         this.ciphertextSegmentSize = ciphertextSegmentSize;
         this.firstSegmentOffset = firstSegmentOffset;
         this.plaintextSegmentSize = ciphertextSegmentSize - tagSizeInBytes;
      }
   }

   private AesCtrHmacStreaming(AesCtrHmacStreamingKey key) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-CTR-HMAC streaming in FIPS-mode.");
      } else {
         this.ikm = key.getInitialKeyMaterial().toByteArray(InsecureSecretKeyAccess.get());
         String hkdfAlgString = "";
         if (key.getParameters().getHkdfHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA1)) {
            hkdfAlgString = "HmacSha1";
         } else if (key.getParameters().getHkdfHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA256)) {
            hkdfAlgString = "HmacSha256";
         } else if (key.getParameters().getHkdfHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA512)) {
            hkdfAlgString = "HmacSha512";
         }

         this.hkdfAlgo = hkdfAlgString;
         this.keySizeInBytes = key.getParameters().getDerivedKeySizeBytes();
         String tagAlgString = "";
         if (key.getParameters().getHmacHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA1)) {
            tagAlgString = "HmacSha1";
         } else if (key.getParameters().getHmacHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA256)) {
            tagAlgString = "HmacSha256";
         } else if (key.getParameters().getHmacHashType().equals(AesCtrHmacStreamingParameters.HashType.SHA512)) {
            tagAlgString = "HmacSha512";
         }

         this.tagAlgo = tagAlgString;
         this.tagSizeInBytes = key.getParameters().getHmacTagSizeBytes();
         this.ciphertextSegmentSize = key.getParameters().getCiphertextSegmentSizeBytes();
         this.firstSegmentOffset = 0;
         this.plaintextSegmentSize = this.ciphertextSegmentSize - this.tagSizeInBytes;
      }
   }

   public static StreamingAead create(AesCtrHmacStreamingKey key) throws GeneralSecurityException {
      return new AesCtrHmacStreaming(key);
   }

   private static void validateParameters(int ikmSize, int keySizeInBytes, String tagAlgo, int tagSizeInBytes, int ciphertextSegmentSize, int firstSegmentOffset) throws InvalidAlgorithmParameterException {
      if (ikmSize >= 16 && ikmSize >= keySizeInBytes) {
         if (firstSegmentOffset < 0) {
            throw new InvalidAlgorithmParameterException("firstSegmentOffset must not be negative");
         } else {
            Validators.validateAesKeySize(keySizeInBytes);
            if (tagSizeInBytes < 10) {
               throw new InvalidAlgorithmParameterException("tag size too small " + tagSizeInBytes);
            } else if ((!tagAlgo.equals("HmacSha1") || tagSizeInBytes <= 20) && (!tagAlgo.equals("HmacSha256") || tagSizeInBytes <= 32) && (!tagAlgo.equals("HmacSha512") || tagSizeInBytes <= 64)) {
               int firstPlaintextSegment = ciphertextSegmentSize - firstSegmentOffset - tagSizeInBytes - keySizeInBytes - 7 - 1;
               if (firstPlaintextSegment <= 0) {
                  throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
               }
            } else {
               throw new InvalidAlgorithmParameterException("tag size too big");
            }
         }
      } else {
         throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, keySizeInBytes));
      }
   }

   public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] aad) throws GeneralSecurityException {
      return new AesCtrHmacStreamEncrypter(aad);
   }

   public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
      return new AesCtrHmacStreamDecrypter();
   }

   public int getCiphertextSegmentSize() {
      return this.ciphertextSegmentSize;
   }

   public int getPlaintextSegmentSize() {
      return this.plaintextSegmentSize;
   }

   public int getHeaderLength() {
      return 1 + this.keySizeInBytes + 7;
   }

   public int getCiphertextOffset() {
      return this.getHeaderLength() + this.firstSegmentOffset;
   }

   public int getCiphertextOverhead() {
      return this.tagSizeInBytes;
   }

   public int getFirstSegmentOffset() {
      return this.firstSegmentOffset;
   }

   public long expectedCiphertextSize(long plaintextSize) {
      long offset = (long)this.getCiphertextOffset();
      long fullSegments = (plaintextSize + offset) / (long)this.plaintextSegmentSize;
      long ciphertextSize = fullSegments * (long)this.ciphertextSegmentSize;
      long lastSegmentSize = (plaintextSize + offset) % (long)this.plaintextSegmentSize;
      if (lastSegmentSize > 0L) {
         ciphertextSize += lastSegmentSize + (long)this.tagSizeInBytes;
      }

      return ciphertextSize;
   }

   private static Cipher cipherInstance() throws GeneralSecurityException {
      return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
   }

   private Mac macInstance() throws GeneralSecurityException {
      return (Mac)EngineFactory.MAC.getInstance(this.tagAlgo);
   }

   private byte[] randomSalt() {
      return Random.randBytes(this.keySizeInBytes);
   }

   private byte[] nonceForSegment(byte[] prefix, long segmentNr, boolean last) throws GeneralSecurityException {
      ByteBuffer nonce = ByteBuffer.allocate(16);
      nonce.order(ByteOrder.BIG_ENDIAN);
      nonce.put(prefix);
      SubtleUtil.putAsUnsigedInt(nonce, segmentNr);
      nonce.put((byte)(last ? 1 : 0));
      nonce.putInt(0);
      return nonce.array();
   }

   private byte[] randomNonce() {
      return Random.randBytes(7);
   }

   private byte[] deriveKeyMaterial(byte[] salt, byte[] aad) throws GeneralSecurityException {
      int keyMaterialSize = this.keySizeInBytes + 32;
      return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, salt, aad, keyMaterialSize);
   }

   private SecretKeySpec deriveKeySpec(byte[] keyMaterial) throws GeneralSecurityException {
      return new SecretKeySpec(keyMaterial, 0, this.keySizeInBytes, "AES");
   }

   private SecretKeySpec deriveHmacKeySpec(byte[] keyMaterial) throws GeneralSecurityException {
      return new SecretKeySpec(keyMaterial, this.keySizeInBytes, 32, this.tagAlgo);
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
   }

   class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
      private final SecretKeySpec keySpec;
      private final SecretKeySpec hmacKeySpec;
      private final Cipher cipher = AesCtrHmacStreaming.cipherInstance();
      private final Mac mac = AesCtrHmacStreaming.this.macInstance();
      private final byte[] noncePrefix;
      private ByteBuffer header;
      private long encryptedSegments = 0L;

      public AesCtrHmacStreamEncrypter(byte[] aad) throws GeneralSecurityException {
         this.encryptedSegments = 0L;
         byte[] salt = AesCtrHmacStreaming.this.randomSalt();
         this.noncePrefix = AesCtrHmacStreaming.this.randomNonce();
         this.header = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
         this.header.put((byte)AesCtrHmacStreaming.this.getHeaderLength());
         this.header.put(salt);
         this.header.put(this.noncePrefix);
         this.header.flip();
         byte[] keymaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(salt, aad);
         this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(keymaterial);
         this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(keymaterial);
      }

      public ByteBuffer getHeader() {
         return this.header.asReadOnlyBuffer();
      }

      public synchronized void encryptSegment(ByteBuffer plaintext, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException {
         int position = ciphertext.position();
         byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, isLastSegment);
         this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
         ++this.encryptedSegments;
         this.cipher.doFinal(plaintext, ciphertext);
         ByteBuffer ctCopy = ciphertext.duplicate();
         ctCopy.flip();
         ctCopy.position(position);
         this.mac.init(this.hmacKeySpec);
         this.mac.update(nonce);
         this.mac.update(ctCopy);
         byte[] tag = this.mac.doFinal();
         ciphertext.put(tag, 0, AesCtrHmacStreaming.this.tagSizeInBytes);
      }

      public synchronized void encryptSegment(ByteBuffer part1, ByteBuffer part2, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException {
         int position = ciphertext.position();
         byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, isLastSegment);
         this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
         ++this.encryptedSegments;
         this.cipher.update(part1, ciphertext);
         this.cipher.doFinal(part2, ciphertext);
         ByteBuffer ctCopy = ciphertext.duplicate();
         ctCopy.flip();
         ctCopy.position(position);
         this.mac.init(this.hmacKeySpec);
         this.mac.update(nonce);
         this.mac.update(ctCopy);
         byte[] tag = this.mac.doFinal();
         ciphertext.put(tag, 0, AesCtrHmacStreaming.this.tagSizeInBytes);
      }
   }

   class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
      private SecretKeySpec keySpec;
      private SecretKeySpec hmacKeySpec;
      private Cipher cipher;
      private Mac mac;
      private byte[] noncePrefix;

      public synchronized void init(ByteBuffer header, byte[] aad) throws GeneralSecurityException {
         if (header.remaining() != AesCtrHmacStreaming.this.getHeaderLength()) {
            throw new InvalidAlgorithmParameterException("Invalid header length");
         } else {
            byte firstByte = header.get();
            if (firstByte != AesCtrHmacStreaming.this.getHeaderLength()) {
               throw new GeneralSecurityException("Invalid ciphertext");
            } else {
               this.noncePrefix = new byte[7];
               byte[] salt = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
               header.get(salt);
               header.get(this.noncePrefix);
               byte[] keymaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(salt, aad);
               this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(keymaterial);
               this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(keymaterial);
               this.cipher = AesCtrHmacStreaming.cipherInstance();
               this.mac = AesCtrHmacStreaming.this.macInstance();
            }
         }
      }

      public synchronized void decryptSegment(ByteBuffer ciphertext, int segmentNr, boolean isLastSegment, ByteBuffer plaintext) throws GeneralSecurityException {
         int position = ciphertext.position();
         byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, (long)segmentNr, isLastSegment);
         int ctLength = ciphertext.remaining();
         if (ctLength < AesCtrHmacStreaming.this.tagSizeInBytes) {
            throw new GeneralSecurityException("Ciphertext too short");
         } else {
            int ptLength = ctLength - AesCtrHmacStreaming.this.tagSizeInBytes;
            int startOfTag = position + ptLength;
            ByteBuffer ct = ciphertext.duplicate();
            ct.limit(startOfTag);
            ByteBuffer tagBuffer = ciphertext.duplicate();
            tagBuffer.position(startOfTag);

            assert this.mac != null;

            assert this.hmacKeySpec != null;

            this.mac.init(this.hmacKeySpec);
            this.mac.update(nonce);
            this.mac.update(ct);
            byte[] tag = this.mac.doFinal();
            tag = Arrays.copyOf(tag, AesCtrHmacStreaming.this.tagSizeInBytes);
            byte[] expectedTag = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];

            assert tagBuffer.remaining() == AesCtrHmacStreaming.this.tagSizeInBytes;

            tagBuffer.get(expectedTag);

            assert expectedTag.length == tag.length;

            if (!Bytes.equal(expectedTag, tag)) {
               throw new GeneralSecurityException("Tag mismatch");
            } else {
               ciphertext.limit(startOfTag);
               this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
               this.cipher.doFinal(ciphertext, plaintext);
            }
         }
      }
   }
}
