package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import java.math.BigInteger;

public class BigIntegerUBytesConverter implements Converter {
   private static final String NEGATIVE_MSG = "JWA Base64urlUInt values MUST be >= 0 (non-negative) per the 'Base64urlUInt' definition in [JWA RFC 7518, Section 2](https://www.rfc-editor.org/rfc/rfc7518.html#section-2)";

   public byte[] applyTo(BigInteger bigInt) {
      Assert.notNull(bigInt, "BigInteger argument cannot be null.");
      if (BigInteger.ZERO.compareTo(bigInt) > 0) {
         throw new IllegalArgumentException("JWA Base64urlUInt values MUST be >= 0 (non-negative) per the 'Base64urlUInt' definition in [JWA RFC 7518, Section 2](https://www.rfc-editor.org/rfc/rfc7518.html#section-2)");
      } else {
         int bitLen = bigInt.bitLength();
         byte[] bytes = bigInt.toByteArray();
         int unsignedByteLen = Math.max(1, Bytes.length(bitLen));
         if (bytes.length == unsignedByteLen) {
            return bytes;
         } else {
            byte[] ubytes = new byte[unsignedByteLen];
            System.arraycopy(bytes, 1, ubytes, 0, unsignedByteLen);
            return ubytes;
         }
      }
   }

   public BigInteger applyFrom(byte[] bytes) {
      Assert.notEmpty(bytes, "Byte array cannot be null or empty.");
      return new BigInteger(1, bytes);
   }
}
