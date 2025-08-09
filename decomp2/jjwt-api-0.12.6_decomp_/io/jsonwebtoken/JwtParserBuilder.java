package io.jsonwebtoken;

import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.lang.Builder;
import io.jsonwebtoken.lang.NestedCollection;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.util.Date;
import javax.crypto.SecretKey;

public interface JwtParserBuilder extends Builder {
   JwtParserBuilder unsecured();

   JwtParserBuilder unsecuredDecompression();

   NestedCollection critical();

   JwtParserBuilder provider(Provider var1);

   JwtParserBuilder requireId(String var1);

   JwtParserBuilder requireSubject(String var1);

   JwtParserBuilder requireAudience(String var1);

   JwtParserBuilder requireIssuer(String var1);

   JwtParserBuilder requireIssuedAt(Date var1);

   JwtParserBuilder requireExpiration(Date var1);

   JwtParserBuilder requireNotBefore(Date var1);

   JwtParserBuilder require(String var1, Object var2);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setClock(Clock var1);

   JwtParserBuilder clock(Clock var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setAllowedClockSkewSeconds(long var1) throws IllegalArgumentException;

   JwtParserBuilder clockSkewSeconds(long var1) throws IllegalArgumentException;

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setSigningKey(byte[] var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setSigningKey(String var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setSigningKey(Key var1);

   JwtParserBuilder verifyWith(SecretKey var1);

   JwtParserBuilder verifyWith(PublicKey var1);

   JwtParserBuilder decryptWith(SecretKey var1);

   JwtParserBuilder decryptWith(PrivateKey var1);

   JwtParserBuilder keyLocator(Locator var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setSigningKeyResolver(SigningKeyResolver var1);

   NestedCollection enc();

   NestedCollection key();

   NestedCollection sig();

   NestedCollection zip();

   /** @deprecated */
   @Deprecated
   JwtParserBuilder setCompressionCodecResolver(CompressionCodecResolver var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder base64UrlDecodeWith(Decoder var1);

   JwtParserBuilder b64Url(Decoder var1);

   /** @deprecated */
   @Deprecated
   JwtParserBuilder deserializeJsonWith(Deserializer var1);

   JwtParserBuilder json(Deserializer var1);

   JwtParser build();
}
