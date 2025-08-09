package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.impl.lang.JwtDateConverter;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Registry;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class DefaultClaims extends ParameterMap implements Claims {
   private static final String CONVERSION_ERROR_MSG = "Cannot convert existing claim value of type '%s' to desired type '%s'. JJWT only converts simple String, Date, Long, Integer, Short and Byte types automatically. Anything more complex is expected to be already converted to your desired type by the JSON Deserializer implementation. You may specify a custom Deserializer for a JwtParser with the desired conversion configuration via the JwtParserBuilder.deserializer() method. See https://github.com/jwtk/jjwt#custom-json-processor for more information. If using Jackson, you can specify custom claim POJO types as described in https://github.com/jwtk/jjwt#json-jackson-custom-types";
   static final Parameter ISSUER = Parameters.string("iss", "Issuer");
   static final Parameter SUBJECT = Parameters.string("sub", "Subject");
   static final Parameter AUDIENCE = Parameters.stringSet("aud", "Audience");
   static final Parameter EXPIRATION = Parameters.rfcDate("exp", "Expiration Time");
   static final Parameter NOT_BEFORE = Parameters.rfcDate("nbf", "Not Before");
   static final Parameter ISSUED_AT = Parameters.rfcDate("iat", "Issued At");
   static final Parameter JTI = Parameters.string("jti", "JWT ID");
   static final Registry PARAMS;

   protected DefaultClaims() {
      super(PARAMS);
   }

   public DefaultClaims(ParameterMap m) {
      super(m.PARAMS, m);
   }

   public DefaultClaims(Map map) {
      super(PARAMS, map);
   }

   public String getName() {
      return "JWT Claims";
   }

   public String getIssuer() {
      return (String)this.get(ISSUER);
   }

   public String getSubject() {
      return (String)this.get(SUBJECT);
   }

   public Set getAudience() {
      return (Set)this.get(AUDIENCE);
   }

   public Date getExpiration() {
      return (Date)this.get(EXPIRATION);
   }

   public Date getNotBefore() {
      return (Date)this.get(NOT_BEFORE);
   }

   public Date getIssuedAt() {
      return (Date)this.get(ISSUED_AT);
   }

   public String getId() {
      return (String)this.get(JTI);
   }

   public Object get(String claimName, Class requiredType) {
      Assert.notNull(requiredType, "requiredType argument cannot be null.");
      Object value = this.idiomaticValues.get(claimName);
      if (requiredType.isInstance(value)) {
         return requiredType.cast(value);
      } else {
         value = this.get(claimName);
         if (value == null) {
            return null;
         } else {
            if (Date.class.equals(requiredType)) {
               try {
                  value = JwtDateConverter.toDate(value);
               } catch (Exception e) {
                  String msg = "Cannot create Date from '" + claimName + "' value '" + value + "'. Cause: " + e.getMessage();
                  throw new IllegalArgumentException(msg, e);
               }
            }

            return this.castClaimValue(claimName, value, requiredType);
         }
      }
   }

   private Object castClaimValue(String name, Object value, Class requiredType) {
      if (value instanceof Long || value instanceof Integer || value instanceof Short || value instanceof Byte) {
         long longValue = ((Number)value).longValue();
         if (Long.class.equals(requiredType)) {
            value = longValue;
         } else if (Integer.class.equals(requiredType) && -2147483648L <= longValue && longValue <= 2147483647L) {
            value = (int)longValue;
         } else if (requiredType == Short.class && -32768L <= longValue && longValue <= 32767L) {
            value = (short)((int)longValue);
         } else if (requiredType == Byte.class && -128L <= longValue && longValue <= 127L) {
            value = (byte)((int)longValue);
         }
      }

      if (!(value instanceof Long) || !requiredType.equals(Integer.class) && !requiredType.equals(Short.class) && !requiredType.equals(Byte.class)) {
         if (!requiredType.isInstance(value)) {
            throw new RequiredTypeException(String.format("Cannot convert existing claim value of type '%s' to desired type '%s'. JJWT only converts simple String, Date, Long, Integer, Short and Byte types automatically. Anything more complex is expected to be already converted to your desired type by the JSON Deserializer implementation. You may specify a custom Deserializer for a JwtParser with the desired conversion configuration via the JwtParserBuilder.deserializer() method. See https://github.com/jwtk/jjwt#custom-json-processor for more information. If using Jackson, you can specify custom claim POJO types as described in https://github.com/jwtk/jjwt#json-jackson-custom-types", value.getClass(), requiredType));
         } else {
            return requiredType.cast(value);
         }
      } else {
         String msg = "Claim '" + name + "' value is too large or too small to be represented as a " + requiredType.getName() + " instance (would cause numeric overflow).";
         throw new RequiredTypeException(msg);
      }
   }

   static {
      PARAMS = Parameters.registry(ISSUER, SUBJECT, AUDIENCE, EXPIRATION, NOT_BEFORE, ISSUED_AT, JTI);
   }
}
