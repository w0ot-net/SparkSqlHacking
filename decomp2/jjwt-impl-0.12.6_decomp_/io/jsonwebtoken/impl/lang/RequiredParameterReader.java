package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.impl.security.JwkContext;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.MalformedKeyException;

public class RequiredParameterReader implements ParameterReadable {
   private final ParameterReadable src;

   public RequiredParameterReader(Header header) {
      this((ParameterReadable)Assert.isInstanceOf(ParameterReadable.class, header, "Header implementations must implement ParameterReadable: "));
   }

   public RequiredParameterReader(ParameterReadable src) {
      this.src = (ParameterReadable)Assert.notNull(src, "Source ParameterReadable cannot be null.");
      Assert.isInstanceOf(Nameable.class, src, "ParameterReadable implementations must implement Nameable.");
   }

   private String name() {
      return ((Nameable)this.src).getName();
   }

   private JwtException malformed(String msg) {
      return (JwtException)(!(this.src instanceof JwkContext) && !(this.src instanceof Jwk) ? new MalformedJwtException(msg) : new MalformedKeyException(msg));
   }

   public Object get(Parameter param) {
      T value = (T)this.src.get(param);
      if (value == null) {
         String msg = this.name() + " is missing required " + param + " value.";
         throw this.malformed(msg);
      } else {
         return value;
      }
   }
}
