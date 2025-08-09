package io.jsonwebtoken;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface Claims extends Map, Identifiable {
   String ISSUER = "iss";
   String SUBJECT = "sub";
   String AUDIENCE = "aud";
   String EXPIRATION = "exp";
   String NOT_BEFORE = "nbf";
   String ISSUED_AT = "iat";
   String ID = "jti";

   String getIssuer();

   String getSubject();

   Set getAudience();

   Date getExpiration();

   Date getNotBefore();

   Date getIssuedAt();

   String getId();

   Object get(String var1, Class var2);
}
