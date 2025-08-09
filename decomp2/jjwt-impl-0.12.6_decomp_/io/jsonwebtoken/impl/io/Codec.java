package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;

public class Codec implements Converter {
   public static final Codec BASE64;
   public static final Codec BASE64URL;
   private final Encoder encoder;
   private final Decoder decoder;

   public Codec(Encoder encoder, Decoder decoder) {
      this.encoder = (Encoder)Assert.notNull(encoder, "Encoder cannot be null.");
      this.decoder = (Decoder)Assert.notNull(decoder, "Decoder cannot be null.");
   }

   public String applyTo(byte[] a) {
      return (String)this.encoder.encode(a);
   }

   public byte[] applyFrom(CharSequence b) {
      try {
         return (byte[])this.decoder.decode(b);
      } catch (DecodingException e) {
         String msg = "Cannot decode input String. Cause: " + e.getMessage();
         throw new IllegalArgumentException(msg, e);
      }
   }

   static {
      BASE64 = new Codec(Encoders.BASE64, Decoders.BASE64);
      BASE64URL = new Codec(Encoders.BASE64URL, Decoders.BASE64URL);
   }
}
