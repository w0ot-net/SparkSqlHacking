package io.jsonwebtoken;

import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.SecurityException;
import java.io.InputStream;

public interface JwtParser extends Parser {
   boolean isSigned(CharSequence var1);

   Jwt parse(CharSequence var1) throws ExpiredJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   Object parse(CharSequence var1, JwtHandler var2) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   Jwt parseContentJwt(CharSequence var1) throws UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   Jwt parseClaimsJwt(CharSequence var1) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   Jws parseContentJws(CharSequence var1) throws UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   Jws parseClaimsJws(CharSequence var1) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.security.SignatureException, SecurityException, IllegalArgumentException;

   Jwt parseUnsecuredContent(CharSequence var1) throws JwtException, IllegalArgumentException;

   Jwt parseUnsecuredClaims(CharSequence var1) throws JwtException, IllegalArgumentException;

   Jws parseSignedContent(CharSequence var1) throws JwtException, IllegalArgumentException;

   Jws parseSignedContent(CharSequence var1, byte[] var2);

   Jws parseSignedContent(CharSequence var1, InputStream var2);

   Jws parseSignedClaims(CharSequence var1) throws JwtException, IllegalArgumentException;

   Jws parseSignedClaims(CharSequence var1, byte[] var2) throws JwtException, IllegalArgumentException;

   Jws parseSignedClaims(CharSequence var1, InputStream var2) throws JwtException, IllegalArgumentException;

   Jwe parseEncryptedContent(CharSequence var1) throws JwtException, IllegalArgumentException;

   Jwe parseEncryptedClaims(CharSequence var1) throws JwtException, IllegalArgumentException;
}
