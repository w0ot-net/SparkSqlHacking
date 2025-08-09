package io.jsonwebtoken;

public interface JwtVisitor {
   Object visit(Jwt var1);

   Object visit(Jws var1);

   Object visit(Jwe var1);
}
