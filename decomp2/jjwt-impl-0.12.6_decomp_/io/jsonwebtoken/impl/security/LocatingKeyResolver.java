package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.lang.Assert;
import java.security.Key;

public class LocatingKeyResolver implements SigningKeyResolver {
   private final Locator locator;

   public LocatingKeyResolver(Locator locator) {
      this.locator = (Locator)Assert.notNull(locator, "Locator cannot be null.");
   }

   public Key resolveSigningKey(JwsHeader header, Claims claims) {
      return (Key)this.locator.locate(header);
   }

   public Key resolveSigningKey(JwsHeader header, byte[] content) {
      return (Key)this.locator.locate(header);
   }
}
