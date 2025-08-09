package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class RequiredBitLengthConverter implements Converter {
   private final Converter converter;
   private final int bitLength;
   private final boolean exact;

   public RequiredBitLengthConverter(Converter converter, int bitLength) {
      this(converter, bitLength, true);
   }

   public RequiredBitLengthConverter(Converter converter, int bitLength, boolean exact) {
      this.converter = (Converter)Assert.notNull(converter, "Converter cannot be null.");
      this.bitLength = (Integer)Assert.gt(bitLength, 0, "bitLength must be greater than 0");
      this.exact = exact;
   }

   private byte[] assertLength(byte[] bytes) {
      long len = Bytes.bitLength(bytes);
      if (this.exact && len != (long)this.bitLength) {
         String msg = "Byte array must be exactly " + Bytes.bitsMsg((long)this.bitLength) + ". Found " + Bytes.bitsMsg(len);
         throw new IllegalArgumentException(msg);
      } else if (len < (long)this.bitLength) {
         String msg = "Byte array must be at least " + Bytes.bitsMsg((long)this.bitLength) + ". Found " + Bytes.bitsMsg(len);
         throw new IllegalArgumentException(msg);
      } else {
         return bytes;
      }
   }

   public Object applyTo(byte[] bytes) {
      this.assertLength(bytes);
      return this.converter.applyTo(bytes);
   }

   public byte[] applyFrom(Object o) {
      byte[] result = (byte[])this.converter.applyFrom(o);
      return this.assertLength(result);
   }
}
