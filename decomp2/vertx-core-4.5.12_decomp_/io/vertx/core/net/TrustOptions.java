package io.vertx.core.net;

import io.vertx.core.Vertx;
import java.util.function.Function;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public interface TrustOptions {
   TrustOptions copy();

   TrustManagerFactory getTrustManagerFactory(Vertx var1) throws Exception;

   Function trustManagerMapper(Vertx var1) throws Exception;

   static TrustOptions wrap(TrustManager trustManager) {
      return new TrustManagerFactoryOptions(trustManager);
   }

   static TrustOptions wrap(TrustManagerFactory trustManagerFactory) {
      return new TrustManagerFactoryOptions(trustManagerFactory);
   }
}
