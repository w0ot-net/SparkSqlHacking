package io.vertx.core.net;

import io.vertx.core.Vertx;
import java.util.function.Function;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

class TrustManagerFactoryOptions implements TrustOptions {
   private final TrustManagerFactory trustManagerFactory;

   TrustManagerFactoryOptions(TrustManagerFactory trustManagerFactory) {
      if (trustManagerFactory != null && trustManagerFactory.getTrustManagers() != null && trustManagerFactory.getTrustManagers().length != 0) {
         this.trustManagerFactory = trustManagerFactory;
      } else {
         throw new IllegalArgumentException("TrustManagerFactory is not present or is not initialized yet");
      }
   }

   TrustManagerFactoryOptions(TrustManager trustManager) {
      this((TrustManagerFactory)(new TrustManagerFactoryWrapper(trustManager)));
   }

   private TrustManagerFactoryOptions(TrustManagerFactoryOptions other) {
      this.trustManagerFactory = other.trustManagerFactory;
   }

   public TrustOptions copy() {
      return new TrustManagerFactoryOptions(this);
   }

   public TrustManagerFactory getTrustManagerFactory(Vertx vertx) {
      return this.trustManagerFactory;
   }

   public Function trustManagerMapper(Vertx vertx) {
      return (serverName) -> this.trustManagerFactory.getTrustManagers();
   }
}
