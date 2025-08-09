package io.vertx.core.net;

import io.vertx.core.Vertx;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.util.function.Function;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;

public interface KeyCertOptions {
   KeyCertOptions copy();

   KeyManagerFactory getKeyManagerFactory(Vertx var1) throws Exception;

   /** @deprecated */
   @Deprecated
   default Function keyManagerMapper(Vertx vertx) throws Exception {
      return (name) -> null;
   }

   default Function keyManagerFactoryMapper(Vertx vertx) throws Exception {
      Function<String, X509KeyManager> mapper = this.keyManagerMapper(vertx);
      return (name) -> {
         X509KeyManager mgr = (X509KeyManager)mapper.apply(name);
         if (mgr != null) {
            try {
               return KeyStoreHelper.toKeyManagerFactory(mgr);
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } else {
            return null;
         }
      };
   }

   static KeyCertOptions wrap(X509KeyManager keyManager) {
      return new KeyManagerFactoryOptions(keyManager);
   }

   static KeyCertOptions wrap(KeyManagerFactory keyManagerFactory) {
      return new KeyManagerFactoryOptions(keyManagerFactory);
   }
}
