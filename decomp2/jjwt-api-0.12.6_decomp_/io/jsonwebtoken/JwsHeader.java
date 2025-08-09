package io.jsonwebtoken;

public interface JwsHeader extends ProtectedHeader {
   /** @deprecated */
   @Deprecated
   String ALGORITHM = "alg";
   /** @deprecated */
   @Deprecated
   String JWK_SET_URL = "jku";
   /** @deprecated */
   @Deprecated
   String JSON_WEB_KEY = "jwk";
   /** @deprecated */
   @Deprecated
   String KEY_ID = "kid";
   /** @deprecated */
   @Deprecated
   String X509_URL = "x5u";
   /** @deprecated */
   @Deprecated
   String X509_CERT_CHAIN = "x5c";
   /** @deprecated */
   @Deprecated
   String X509_CERT_SHA1_THUMBPRINT = "x5t";
   /** @deprecated */
   @Deprecated
   String X509_CERT_SHA256_THUMBPRINT = "x5t#S256";
   /** @deprecated */
   @Deprecated
   String CRITICAL = "crit";

   boolean isPayloadEncoded();
}
