package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;

class ExceptionPropagatingDecoder implements Decoder {
   private final Decoder decoder;

   ExceptionPropagatingDecoder(Decoder decoder) {
      Assert.notNull(decoder, "Decoder cannot be null.");
      this.decoder = decoder;
   }

   public Object decode(Object t) throws DecodingException {
      Assert.notNull(t, "Decode argument cannot be null.");

      try {
         return this.decoder.decode(t);
      } catch (DecodingException e) {
         throw e;
      } catch (Exception e) {
         String msg = "Unable to decode input: " + e.getMessage();
         throw new DecodingException(msg, e);
      }
   }
}
