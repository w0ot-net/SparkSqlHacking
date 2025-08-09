package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.Codec;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Converters;
import java.math.BigInteger;

final class FieldElementConverter implements Converter {
   static final FieldElementConverter INSTANCE = new FieldElementConverter();
   static final Converter B64URL_CONVERTER;
   private static final int P256_BYTE_LEN;
   private static final int P384_BYTE_LEN;
   private static final int P521_BYTE_LEN;

   private static int bytelen(ECCurve curve) {
      return Bytes.length(curve.toParameterSpec().getCurve().getField().getFieldSize());
   }

   public byte[] applyTo(BigInteger bigInteger) {
      byte[] bytes = (byte[])Converters.BIGINT_UBYTES.applyTo(bigInteger);
      int len = bytes.length;
      if (len != P256_BYTE_LEN && len != P384_BYTE_LEN && len != P521_BYTE_LEN) {
         if (len < P256_BYTE_LEN) {
            bytes = Bytes.prepad(bytes, P256_BYTE_LEN);
         } else if (len < P384_BYTE_LEN) {
            bytes = Bytes.prepad(bytes, P384_BYTE_LEN);
         } else {
            bytes = Bytes.prepad(bytes, P521_BYTE_LEN);
         }

         return bytes;
      } else {
         return bytes;
      }
   }

   public BigInteger applyFrom(byte[] bytes) {
      return (BigInteger)Converters.BIGINT_UBYTES.applyFrom(bytes);
   }

   static {
      B64URL_CONVERTER = Converters.forEncoded(BigInteger.class, Converters.compound(INSTANCE, Codec.BASE64URL));
      P256_BYTE_LEN = bytelen(ECCurve.P256);
      P384_BYTE_LEN = bytelen(ECCurve.P384);
      P521_BYTE_LEN = bytelen(ECCurve.P521);
   }
}
