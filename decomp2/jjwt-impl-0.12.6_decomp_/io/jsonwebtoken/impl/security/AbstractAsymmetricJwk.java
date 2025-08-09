package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.AsymmetricJwk;
import java.net.URI;
import java.util.List;
import java.util.Set;

public abstract class AbstractAsymmetricJwk extends AbstractJwk implements AsymmetricJwk {
   static final Parameter USE = Parameters.string("use", "Public Key Use");
   public static final Parameter X5C = Parameters.x509Chain("x5c", "X.509 Certificate Chain");
   public static final Parameter X5T = (Parameter)Parameters.bytes("x5t", "X.509 Certificate SHA-1 Thumbprint").build();
   public static final Parameter X5T_S256 = (Parameter)Parameters.bytes("x5t#S256", "X.509 Certificate SHA-256 Thumbprint").build();
   public static final Parameter X5U = Parameters.uri("x5u", "X.509 URL");
   static final Set PARAMS;

   AbstractAsymmetricJwk(JwkContext ctx, List thumbprintParams) {
      super(ctx, thumbprintParams);
   }

   public String getPublicKeyUse() {
      return this.context.getPublicKeyUse();
   }

   public URI getX509Url() {
      return this.context.getX509Url();
   }

   public List getX509Chain() {
      return Collections.immutable(this.context.getX509Chain());
   }

   public byte[] getX509Sha1Thumbprint() {
      return (byte[])Arrays.copy(this.context.getX509Sha1Thumbprint());
   }

   public byte[] getX509Sha256Thumbprint() {
      return (byte[])Arrays.copy(this.context.getX509Sha256Thumbprint());
   }

   static {
      PARAMS = Collections.concat(AbstractJwk.PARAMS, new Parameter[]{USE, X5C, X5T, X5T_S256, X5U});
   }
}
