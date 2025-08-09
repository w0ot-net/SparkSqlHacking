package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import java.security.Key;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public final class StandardSecureDigestAlgorithms extends IdRegistry {
   public static final String NAME = "JWS Digital Signature or MAC";

   public StandardSecureDigestAlgorithms() {
      super("JWS Digital Signature or MAC", Collections.of(new SecureDigestAlgorithm[]{NoneSignatureAlgorithm.INSTANCE, DefaultMacAlgorithm.HS256, DefaultMacAlgorithm.HS384, DefaultMacAlgorithm.HS512, RsaSignatureAlgorithm.RS256, RsaSignatureAlgorithm.RS384, RsaSignatureAlgorithm.RS512, RsaSignatureAlgorithm.PS256, RsaSignatureAlgorithm.PS384, RsaSignatureAlgorithm.PS512, EcSignatureAlgorithm.ES256, EcSignatureAlgorithm.ES384, EcSignatureAlgorithm.ES512, EdSignatureAlgorithm.INSTANCE}));
   }

   public static SecureDigestAlgorithm findBySigningKey(Key key) {
      SecureDigestAlgorithm<?, ?> alg = null;
      if (key instanceof SecretKey && !(key instanceof Password)) {
         alg = DefaultMacAlgorithm.findByKey(key);
      } else if (key instanceof PrivateKey) {
         PrivateKey pk = (PrivateKey)key;
         alg = RsaSignatureAlgorithm.findByKey(pk);
         if (alg == null) {
            alg = EcSignatureAlgorithm.findByKey(pk);
         }

         if (alg == null && EdSignatureAlgorithm.isSigningKey(pk)) {
            alg = EdSignatureAlgorithm.INSTANCE;
         }
      }

      return alg;
   }
}
