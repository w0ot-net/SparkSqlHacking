package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

final class ConcatKDF extends CryptoAlgorithm {
   private static final long MAX_REP_COUNT = 4294967295L;
   private static final long MAX_HASH_INPUT_BYTE_LENGTH = 2147483647L;
   private static final long MAX_HASH_INPUT_BIT_LENGTH = 17179869176L;
   private final int hashBitLength;
   private static final long MAX_DERIVED_KEY_BIT_LENGTH = 17179869176L;

   ConcatKDF(String jcaName) {
      super("ConcatKDF", jcaName);
      int hashByteLength = (Integer)this.jca().withMessageDigest(new CheckedFunction() {
         public Integer apply(MessageDigest instance) {
            return instance.getDigestLength();
         }
      });
      this.hashBitLength = hashByteLength * 8;
      Assert.state(this.hashBitLength > 0, "MessageDigest length must be a positive value.");
   }

   public SecretKey deriveKey(final byte[] Z, final long derivedKeyBitLength, byte[] otherInfo) throws UnsupportedKeyException, SecurityException {
      Assert.notEmpty(Z, "Z cannot be null or empty.");
      Assert.isTrue(derivedKeyBitLength > 0L, "derivedKeyBitLength must be a positive integer.");
      if (derivedKeyBitLength > 17179869176L) {
         String msg = "derivedKeyBitLength may not exceed " + Bytes.bitsMsg(17179869176L) + ". Specified size: " + Bytes.bitsMsg(derivedKeyBitLength) + ".";
         throw new IllegalArgumentException(msg);
      } else {
         long derivedKeyByteLength = derivedKeyBitLength / 8L;
         final byte[] OtherInfo = otherInfo == null ? Bytes.EMPTY : otherInfo;
         double repsd = (double)derivedKeyBitLength / (double)this.hashBitLength;
         final long reps = (long)Math.ceil(repsd);
         final boolean kLastPartial = repsd != (double)reps;
         Assert.state(reps <= 4294967295L, "derivedKeyBitLength is too large.");
         final byte[] counter = new byte[]{0, 0, 0, 1};
         long inputBitLength = Bytes.bitLength(counter) + Bytes.bitLength(Z) + Bytes.bitLength(OtherInfo);
         Assert.state(inputBitLength <= 17179869176L, "Hash input is too large.");
         final ClearableByteArrayOutputStream stream = new ClearableByteArrayOutputStream((int)derivedKeyByteLength);
         byte[] derivedKeyBytes = Bytes.EMPTY;

         SecretKeySpec var18;
         try {
            derivedKeyBytes = (byte[])this.jca().withMessageDigest(new CheckedFunction() {
               public byte[] apply(MessageDigest md) throws Exception {
                  for(long i = 1L; i <= reps; ++i) {
                     md.update(counter);
                     md.update(Z);
                     md.update(OtherInfo);
                     byte[] Ki = md.digest();
                     Bytes.increment(counter);
                     if (i == reps && kLastPartial) {
                        long leftmostBitLength = derivedKeyBitLength % (long)ConcatKDF.this.hashBitLength;
                        int leftmostByteLength = (int)(leftmostBitLength / 8L);
                        byte[] kLast = new byte[leftmostByteLength];
                        System.arraycopy(Ki, 0, kLast, 0, kLast.length);
                        Ki = kLast;
                     }

                     stream.write(Ki);
                  }

                  return stream.toByteArray();
               }
            });
            var18 = new SecretKeySpec(derivedKeyBytes, "AES");
         } finally {
            Bytes.clear(derivedKeyBytes);
            Bytes.clear(counter);
            stream.reset();
         }

         return var18;
      }
   }

   private static class ClearableByteArrayOutputStream extends ByteArrayOutputStream {
      public ClearableByteArrayOutputStream(int size) {
         super(size);
      }

      public synchronized void reset() {
         super.reset();
         Bytes.clear(this.buf);
      }
   }
}
