package io.jsonwebtoken.impl;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.security.AbstractAsymmetricJwk;
import io.jsonwebtoken.security.X509Mutator;
import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;

public class AbstractX509Context extends ParameterMap implements X509Context {
   public AbstractX509Context(Set params) {
      super(params);
   }

   protected X509Mutator self() {
      return this;
   }

   public URI getX509Url() {
      return (URI)this.get(AbstractAsymmetricJwk.X5U);
   }

   public X509Mutator x509Url(URI uri) {
      this.put(AbstractAsymmetricJwk.X5U, uri);
      return this.self();
   }

   public List getX509Chain() {
      return (List)this.get(AbstractAsymmetricJwk.X5C);
   }

   public X509Mutator x509Chain(List chain) {
      this.put(AbstractAsymmetricJwk.X5C, chain);
      return this.self();
   }

   public byte[] getX509Sha1Thumbprint() {
      return (byte[])this.get(AbstractAsymmetricJwk.X5T);
   }

   public X509Mutator x509Sha1Thumbprint(byte[] thumbprint) {
      this.put(AbstractAsymmetricJwk.X5T, thumbprint);
      return this.self();
   }

   public byte[] getX509Sha256Thumbprint() {
      return (byte[])this.get(AbstractAsymmetricJwk.X5T_S256);
   }

   public X509Mutator x509Sha256Thumbprint(byte[] thumbprint) {
      this.put(AbstractAsymmetricJwk.X5T_S256, thumbprint);
      return this.self();
   }
}
