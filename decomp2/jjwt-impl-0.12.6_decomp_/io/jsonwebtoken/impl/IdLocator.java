package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;

public class IdLocator implements Locator, Function {
   private final Parameter param;
   private final String requiredMsg;
   private final boolean valueRequired;
   private final Registry registry;

   public IdLocator(Parameter param, Registry registry, String requiredExceptionMessage) {
      this.param = (Parameter)Assert.notNull(param, "Header param cannot be null.");
      this.requiredMsg = Strings.clean(requiredExceptionMessage);
      this.valueRequired = Strings.hasText(this.requiredMsg);
      Assert.notEmpty(registry, "Registry cannot be null or empty.");
      this.registry = registry;
   }

   private static String type(Header header) {
      if (header instanceof JweHeader) {
         return "JWE";
      } else {
         return header instanceof JwsHeader ? "JWS" : "JWT";
      }
   }

   public Identifiable locate(Header header) {
      Assert.notNull(header, "Header argument cannot be null.");
      Object val = header.get(this.param.getId());
      String id = val != null ? val.toString() : null;
      if (!Strings.hasText(id)) {
         if (this.valueRequired) {
            throw new MalformedJwtException(this.requiredMsg);
         } else {
            return null;
         }
      } else {
         try {
            return (Identifiable)this.registry.forKey(id);
         } catch (Exception e) {
            String msg = "Unrecognized " + type(header) + " " + this.param + " header value: " + id;
            throw new UnsupportedJwtException(msg, e);
         }
      }
   }

   public Identifiable apply(Header header) {
      return this.locate(header);
   }
}
