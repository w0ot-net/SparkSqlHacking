package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import java.net.URI;

public class UriStringConverter implements Converter {
   public String applyTo(URI uri) {
      Assert.notNull(uri, "URI cannot be null.");
      return uri.toString();
   }

   public URI applyFrom(CharSequence s) {
      Assert.hasText(s, "URI string cannot be null or empty.");

      try {
         return URI.create(s.toString());
      } catch (Exception e) {
         String msg = "Unable to convert String value '" + s + "' to URI instance: " + e.getMessage();
         throw new IllegalArgumentException(msg, e);
      }
   }
}
