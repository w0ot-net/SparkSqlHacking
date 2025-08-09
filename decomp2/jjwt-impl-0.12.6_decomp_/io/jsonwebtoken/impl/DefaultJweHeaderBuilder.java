package io.jsonwebtoken.impl;

import io.jsonwebtoken.JweHeaderMutator;
import io.jsonwebtoken.security.X509Builder;

public class DefaultJweHeaderBuilder extends DefaultJweHeaderMutator implements X509Builder {
   protected DefaultJweHeaderBuilder() {
   }

   protected DefaultJweHeaderBuilder(DefaultJweHeaderMutator src) {
      super(src);
   }

   public JweHeaderMutator x509Sha1Thumbprint(boolean enable) {
      this.x509.x509Sha1Thumbprint(enable);
      return (JweHeaderMutator)this.self();
   }

   public JweHeaderMutator x509Sha256Thumbprint(boolean enable) {
      this.x509.x509Sha256Thumbprint(enable);
      return (JweHeaderMutator)this.self();
   }
}
