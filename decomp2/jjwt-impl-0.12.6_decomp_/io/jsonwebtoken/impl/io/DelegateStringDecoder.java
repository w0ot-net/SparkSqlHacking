package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import java.io.InputStream;

/** @deprecated */
@Deprecated
public class DelegateStringDecoder implements Decoder {
   private final Decoder delegate;

   public DelegateStringDecoder(Decoder delegate) {
      this.delegate = (Decoder)Assert.notNull(delegate, "delegate cannot be null.");
   }

   public InputStream decode(InputStream in) throws DecodingException {
      try {
         byte[] data = Streams.bytes(in, "Unable to Base64URL-decode input.");
         data = (byte[])this.delegate.decode(Strings.utf8(data));
         return Streams.of(data);
      } catch (Throwable t) {
         String msg = "Unable to Base64Url-decode InputStream: " + t.getMessage();
         throw new DecodingException(msg, t);
      }
   }
}
