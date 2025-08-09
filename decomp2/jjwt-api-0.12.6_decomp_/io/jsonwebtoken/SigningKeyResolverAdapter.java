package io.jsonwebtoken;

import io.jsonwebtoken.lang.Assert;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

/** @deprecated */
@Deprecated
public class SigningKeyResolverAdapter implements SigningKeyResolver {
   public Key resolveSigningKey(JwsHeader header, Claims claims) {
      SignatureAlgorithm alg = SignatureAlgorithm.forName(header.getAlgorithm());
      Assert.isTrue(alg.isHmac(), "The default resolveSigningKey(JwsHeader, Claims) implementation cannot be used for asymmetric key algorithms (RSA, Elliptic Curve).  Override the resolveSigningKey(JwsHeader, Claims) method instead and return a Key instance appropriate for the " + alg.name() + " algorithm.");
      byte[] keyBytes = this.resolveSigningKeyBytes(header, claims);
      return new SecretKeySpec(keyBytes, alg.getJcaName());
   }

   public Key resolveSigningKey(JwsHeader header, byte[] content) {
      SignatureAlgorithm alg = SignatureAlgorithm.forName(header.getAlgorithm());
      Assert.isTrue(alg.isHmac(), "The default resolveSigningKey(JwsHeader, byte[]) implementation cannot be used for asymmetric key algorithms (RSA, Elliptic Curve).  Override the resolveSigningKey(JwsHeader, byte[]) method instead and return a Key instance appropriate for the " + alg.name() + " algorithm.");
      byte[] keyBytes = this.resolveSigningKeyBytes(header, content);
      return new SecretKeySpec(keyBytes, alg.getJcaName());
   }

   public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
      throw new UnsupportedJwtException("The specified SigningKeyResolver implementation does not support Claims JWS signing key resolution.  Consider overriding either the resolveSigningKey(JwsHeader, Claims) method or, for HMAC algorithms, the resolveSigningKeyBytes(JwsHeader, Claims) method.");
   }

   public byte[] resolveSigningKeyBytes(JwsHeader header, byte[] content) {
      throw new UnsupportedJwtException("The specified SigningKeyResolver implementation does not support content JWS signing key resolution.  Consider overriding either the resolveSigningKey(JwsHeader, byte[]) method or, for HMAC algorithms, the resolveSigningKeyBytes(JwsHeader, byte[]) method.");
   }
}
