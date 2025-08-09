package io.jsonwebtoken.impl;

import io.jsonwebtoken.JweHeaderMutator;
import io.jsonwebtoken.impl.lang.DefaultNestedCollection;
import io.jsonwebtoken.impl.lang.DelegatingMapMutator;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.security.X509BuilderSupport;
import io.jsonwebtoken.lang.NestedCollection;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.PublicJwk;
import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;

public class DefaultJweHeaderMutator extends DelegatingMapMutator implements JweHeaderMutator {
   protected X509BuilderSupport x509;

   public DefaultJweHeaderMutator() {
      super(new ParameterMap(DefaultJweHeader.PARAMS));
      this.clear();
   }

   public DefaultJweHeaderMutator(DefaultJweHeaderMutator src) {
      super(src.DELEGATE);
      this.x509 = src.x509;
   }

   private JweHeaderMutator put(Parameter param, Object value) {
      ((ParameterMap)this.DELEGATE).put(param, value);
      return (JweHeaderMutator)this.self();
   }

   public void clear() {
      super.clear();
      this.x509 = new X509BuilderSupport((ParameterMap)this.DELEGATE, IllegalStateException.class);
   }

   public JweHeaderMutator contentType(String cty) {
      return this.put(DefaultHeader.CONTENT_TYPE, cty);
   }

   public JweHeaderMutator type(String typ) {
      return this.put(DefaultHeader.TYPE, typ);
   }

   public JweHeaderMutator setType(String typ) {
      return this.type(typ);
   }

   public JweHeaderMutator setContentType(String cty) {
      return this.contentType(cty);
   }

   public JweHeaderMutator setCompressionAlgorithm(String zip) {
      return this.put(DefaultHeader.COMPRESSION_ALGORITHM, zip);
   }

   public NestedCollection critical() {
      // $FF: Couldn't be decompiled
   }

   public JweHeaderMutator jwk(PublicJwk jwk) {
      return this.put(DefaultProtectedHeader.JWK, jwk);
   }

   public JweHeaderMutator jwkSetUrl(URI uri) {
      return this.put(DefaultProtectedHeader.JKU, uri);
   }

   public JweHeaderMutator keyId(String kid) {
      return this.put(DefaultProtectedHeader.KID, kid);
   }

   public JweHeaderMutator setKeyId(String kid) {
      return this.keyId(kid);
   }

   public JweHeaderMutator setAlgorithm(String alg) {
      return this.put(DefaultHeader.ALGORITHM, alg);
   }

   public JweHeaderMutator x509Url(URI uri) {
      this.x509.x509Url(uri);
      return (JweHeaderMutator)this.self();
   }

   public JweHeaderMutator x509Chain(List chain) {
      this.x509.x509Chain(chain);
      return (JweHeaderMutator)this.self();
   }

   public JweHeaderMutator x509Sha1Thumbprint(byte[] thumbprint) {
      this.x509.x509Sha1Thumbprint(thumbprint);
      return (JweHeaderMutator)this.self();
   }

   public JweHeaderMutator x509Sha256Thumbprint(byte[] thumbprint) {
      this.x509.x509Sha256Thumbprint(thumbprint);
      return (JweHeaderMutator)this.self();
   }

   public JweHeaderMutator agreementPartyUInfo(byte[] info) {
      return this.put(DefaultJweHeader.APU, info);
   }

   public JweHeaderMutator agreementPartyUInfo(String info) {
      return this.agreementPartyUInfo(Strings.utf8(Strings.clean(info)));
   }

   public JweHeaderMutator agreementPartyVInfo(byte[] info) {
      return this.put(DefaultJweHeader.APV, info);
   }

   public JweHeaderMutator agreementPartyVInfo(String info) {
      return this.agreementPartyVInfo(Strings.utf8(Strings.clean(info)));
   }

   public JweHeaderMutator pbes2Count(int count) {
      return this.put(DefaultJweHeader.P2C, count);
   }

   // $FF: synthetic method
   static JweHeaderMutator access$000(DefaultJweHeaderMutator x0, Parameter x1, Object x2) {
      return x0.put(x1, x2);
   }
}
