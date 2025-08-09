package io.jsonwebtoken;

import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Conjunctor;
import io.jsonwebtoken.lang.MapMutator;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.X509Builder;
import java.io.InputStream;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

public interface JwtBuilder extends ClaimsMutator {
   JwtBuilder provider(Provider var1);

   JwtBuilder random(SecureRandom var1);

   BuilderHeader header();

   /** @deprecated */
   @Deprecated
   JwtBuilder setHeader(Map var1);

   /** @deprecated */
   @Deprecated
   JwtBuilder setHeaderParams(Map var1);

   /** @deprecated */
   @Deprecated
   JwtBuilder setHeaderParam(String var1, Object var2);

   /** @deprecated */
   @Deprecated
   JwtBuilder setPayload(String var1);

   JwtBuilder content(String var1);

   JwtBuilder content(byte[] var1);

   JwtBuilder content(InputStream var1);

   JwtBuilder content(String var1, String var2) throws IllegalArgumentException;

   JwtBuilder content(byte[] var1, String var2) throws IllegalArgumentException;

   JwtBuilder content(InputStream var1, String var2) throws IllegalArgumentException;

   BuilderClaims claims();

   /** @deprecated */
   @Deprecated
   JwtBuilder setClaims(Map var1);

   /** @deprecated */
   @Deprecated
   JwtBuilder addClaims(Map var1);

   JwtBuilder claim(String var1, Object var2);

   JwtBuilder claims(Map var1);

   JwtBuilder issuer(String var1);

   JwtBuilder subject(String var1);

   JwtBuilder expiration(Date var1);

   JwtBuilder notBefore(Date var1);

   JwtBuilder issuedAt(Date var1);

   JwtBuilder id(String var1);

   JwtBuilder signWith(Key var1) throws InvalidKeyException;

   /** @deprecated */
   @Deprecated
   JwtBuilder signWith(SignatureAlgorithm var1, byte[] var2) throws InvalidKeyException;

   /** @deprecated */
   @Deprecated
   JwtBuilder signWith(SignatureAlgorithm var1, String var2) throws InvalidKeyException;

   /** @deprecated */
   @Deprecated
   JwtBuilder signWith(SignatureAlgorithm var1, Key var2) throws InvalidKeyException;

   /** @deprecated */
   @Deprecated
   JwtBuilder signWith(Key var1, SignatureAlgorithm var2) throws InvalidKeyException;

   JwtBuilder signWith(Key var1, SecureDigestAlgorithm var2) throws InvalidKeyException;

   JwtBuilder encryptWith(SecretKey var1, AeadAlgorithm var2);

   JwtBuilder encryptWith(Key var1, KeyAlgorithm var2, AeadAlgorithm var3);

   JwtBuilder compressWith(CompressionAlgorithm var1);

   /** @deprecated */
   @Deprecated
   JwtBuilder base64UrlEncodeWith(Encoder var1);

   JwtBuilder b64Url(Encoder var1);

   JwtBuilder encodePayload(boolean var1);

   /** @deprecated */
   @Deprecated
   JwtBuilder serializeToJsonWith(Serializer var1);

   JwtBuilder json(Serializer var1);

   String compact();

   public interface BuilderClaims extends MapMutator, ClaimsMutator, Conjunctor {
   }

   public interface BuilderHeader extends JweHeaderMutator, X509Builder, Conjunctor {
   }
}
