package io.jsonwebtoken;

/** @deprecated */
@Deprecated
public interface JwtHandler extends JwtVisitor {
   Object onContentJwt(Jwt var1);

   Object onClaimsJwt(Jwt var1);

   Object onContentJws(Jws var1);

   Object onClaimsJws(Jws var1);

   Object onContentJwe(Jwe var1);

   Object onClaimsJwe(Jwe var1);
}
