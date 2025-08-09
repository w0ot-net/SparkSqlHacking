package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.ParameterMap;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.lang.Functions;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.X509Builder;
import io.jsonwebtoken.security.Jwks.HASH;
import java.io.InputStream;
import java.net.URI;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

public class X509BuilderSupport implements X509Builder {
   private final ParameterMap map;
   protected boolean computeX509Sha1Thumbprint;
   protected Boolean computeX509Sha256Thumbprint = null;
   private final Function GET_X509_BYTES;

   private static Function createGetBytesFunction(Class clazz) {
      return Functions.wrapFmt(new CheckedFunction() {
         public byte[] apply(X509Certificate cert) throws Exception {
            return cert.getEncoded();
         }
      }, clazz, "Unable to access X509Certificate encoded bytes necessary to compute thumbprint. Certificate: %s");
   }

   public X509BuilderSupport(ParameterMap map, Class getBytesFailedException) {
      this.map = (ParameterMap)Assert.notNull(map, "ParameterMap cannot be null.");
      this.GET_X509_BYTES = createGetBytesFunction(getBytesFailedException);
   }

   public X509BuilderSupport x509Url(URI uri) {
      this.map.put((String)AbstractAsymmetricJwk.X5U.getId(), uri);
      return this;
   }

   public X509BuilderSupport x509Chain(List chain) {
      this.map.put((String)AbstractAsymmetricJwk.X5C.getId(), chain);
      return this;
   }

   public X509BuilderSupport x509Sha1Thumbprint(byte[] thumbprint) {
      this.map.put((String)AbstractAsymmetricJwk.X5T.getId(), thumbprint);
      return this;
   }

   public X509BuilderSupport x509Sha1Thumbprint(boolean enable) {
      this.computeX509Sha1Thumbprint = enable;
      return this;
   }

   public X509BuilderSupport x509Sha256Thumbprint(byte[] thumbprint) {
      this.map.put((String)AbstractAsymmetricJwk.X5T_S256.getId(), thumbprint);
      return this;
   }

   public X509BuilderSupport x509Sha256Thumbprint(boolean enable) {
      this.computeX509Sha256Thumbprint = enable;
      return this;
   }

   private byte[] computeThumbprint(X509Certificate cert, HashAlgorithm alg) {
      byte[] encoded = (byte[])this.GET_X509_BYTES.apply(cert);
      InputStream in = Streams.of(encoded);
      Request<InputStream> request = new DefaultRequest(in, (Provider)null, (SecureRandom)null);
      return alg.digest(request);
   }

   public void apply() {
      List<X509Certificate> chain = (List)this.map.get(AbstractAsymmetricJwk.X5C);
      X509Certificate firstCert = null;
      if (!Collections.isEmpty(chain)) {
         firstCert = (X509Certificate)chain.get(0);
      }

      Boolean computeX509Sha256 = this.computeX509Sha256Thumbprint;
      if (computeX509Sha256 == null) {
         computeX509Sha256 = firstCert != null && !this.computeX509Sha1Thumbprint && Objects.isEmpty((byte[])this.map.get(AbstractAsymmetricJwk.X5T_S256));
      }

      if (firstCert != null) {
         if (this.computeX509Sha1Thumbprint) {
            byte[] thumbprint = this.computeThumbprint(firstCert, DefaultHashAlgorithm.SHA1);
            this.x509Sha1Thumbprint(thumbprint);
         }

         if (computeX509Sha256) {
            byte[] thumbprint = this.computeThumbprint(firstCert, HASH.SHA256);
            this.x509Sha256Thumbprint(thumbprint);
         }
      }

   }
}
