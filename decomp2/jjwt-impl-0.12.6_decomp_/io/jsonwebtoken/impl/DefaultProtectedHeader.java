package io.jsonwebtoken.impl;

import io.jsonwebtoken.ProtectedHeader;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.impl.security.AbstractAsymmetricJwk;
import io.jsonwebtoken.impl.security.AbstractJwk;
import io.jsonwebtoken.impl.security.JwkConverter;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.PublicJwk;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultProtectedHeader extends DefaultHeader implements ProtectedHeader {
   static final Parameter JKU = Parameters.uri("jku", "JWK Set URL");
   static final Parameter JWK;
   static final Parameter CRIT;
   static final Parameter KID;
   static final Parameter X5U;
   static final Parameter X5C;
   static final Parameter X5T;
   static final Parameter X5T_S256;
   static final Registry PARAMS;

   static boolean isCandidate(ParameterMap map) {
      String id = (String)map.get(DefaultHeader.ALGORITHM);
      return Strings.hasText(id) && !id.equalsIgnoreCase(SIG.NONE.getId());
   }

   protected DefaultProtectedHeader(Registry registry, Map values) {
      super(registry, values);
   }

   public String getKeyId() {
      return (String)this.get(KID);
   }

   public URI getJwkSetUrl() {
      return (URI)this.get(JKU);
   }

   public PublicJwk getJwk() {
      return (PublicJwk)this.get(JWK);
   }

   public URI getX509Url() {
      return (URI)this.get(AbstractAsymmetricJwk.X5U);
   }

   public List getX509Chain() {
      return (List)this.get(X5C);
   }

   public byte[] getX509Sha1Thumbprint() {
      return (byte[])this.get(X5T);
   }

   public byte[] getX509Sha256Thumbprint() {
      return (byte[])this.get(X5T_S256);
   }

   public Set getCritical() {
      return (Set)this.get(CRIT);
   }

   static {
      JWK = (Parameter)Parameters.builder(JwkConverter.PUBLIC_JWK_CLASS).setId("jwk").setName("JSON Web Key").setConverter(JwkConverter.PUBLIC_JWK).build();
      CRIT = Parameters.stringSet("crit", "Critical");
      KID = AbstractJwk.KID;
      X5U = AbstractAsymmetricJwk.X5U;
      X5C = AbstractAsymmetricJwk.X5C;
      X5T = AbstractAsymmetricJwk.X5T;
      X5T_S256 = AbstractAsymmetricJwk.X5T_S256;
      PARAMS = Parameters.registry(DefaultHeader.PARAMS, CRIT, JKU, JWK, KID, X5U, X5C, X5T, X5T_S256);
   }
}
