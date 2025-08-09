package io.vertx.core.net;

import io.vertx.core.Vertx;
import java.util.function.Function;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;

class KeyManagerFactoryOptions implements KeyCertOptions {
   private final KeyManagerFactory keyManagerFactory;

   KeyManagerFactoryOptions(KeyManagerFactory keyManagerFactory) {
      if (keyManagerFactory != null && keyManagerFactory.getKeyManagers() != null && keyManagerFactory.getKeyManagers().length != 0) {
         this.keyManagerFactory = keyManagerFactory;
      } else {
         throw new IllegalArgumentException("KeyManagerFactory is not present or is not initialized yet");
      }
   }

   KeyManagerFactoryOptions(X509KeyManager keyManager) {
      this((KeyManagerFactory)(new KeyManagerFactoryWrapper(keyManager)));
   }

   private KeyManagerFactoryOptions(KeyManagerFactoryOptions other) {
      this.keyManagerFactory = other.keyManagerFactory;
   }

   public KeyCertOptions copy() {
      return new KeyManagerFactoryOptions(this);
   }

   public KeyManagerFactory getKeyManagerFactory(Vertx vertx) {
      return this.keyManagerFactory;
   }

   public Function keyManagerMapper(Vertx vertx) {
      return this.keyManagerFactory.getKeyManagers()[0] instanceof X509KeyManager ? (serverName) -> (X509KeyManager)this.keyManagerFactory.getKeyManagers()[0] : null;
   }
}
