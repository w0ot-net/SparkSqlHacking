package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;

class ExceptionPropagatingEncoder implements Encoder {
   private final Encoder encoder;

   ExceptionPropagatingEncoder(Encoder encoder) {
      Assert.notNull(encoder, "Encoder cannot be null.");
      this.encoder = encoder;
   }

   public Object encode(Object t) throws EncodingException {
      Assert.notNull(t, "Encode argument cannot be null.");

      try {
         return this.encoder.encode(t);
      } catch (EncodingException e) {
         throw e;
      } catch (Exception e) {
         String msg = "Unable to encode input: " + e.getMessage();
         throw new EncodingException(msg, e);
      }
   }
}
