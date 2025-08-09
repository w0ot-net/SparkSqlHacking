package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.LocatorAdapter;
import io.jsonwebtoken.impl.lang.Function;
import java.security.Key;

public class ConstantKeyLocator extends LocatorAdapter implements Function {
   private final Key jwsKey;
   private final Key jweKey;

   public ConstantKeyLocator(Key jwsKey, Key jweKey) {
      this.jwsKey = jwsKey;
      this.jweKey = jweKey;
   }

   protected Key locate(JwsHeader header) {
      return this.jwsKey;
   }

   protected Key locate(JweHeader header) {
      return this.jweKey;
   }

   public Key apply(Header header) {
      return (Key)this.locate((Header)header);
   }
}
