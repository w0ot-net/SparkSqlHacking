package io.vertx.core.net;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Objects;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TrustManagerFactoryWrapper extends TrustManagerFactory {
   private static final Logger LOGGER = LoggerFactory.getLogger(TrustManagerFactoryWrapper.class);
   private static final String KEY_MANAGER_FACTORY_ALGORITHM = "no-algorithm";
   private static final Provider PROVIDER = new Provider("", (double)1.0F, "") {
   };

   TrustManagerFactoryWrapper(TrustManager trustManager) {
      super(new TrustManagerFactorySpiWrapper(trustManager), PROVIDER, "no-algorithm");
   }

   private static class TrustManagerFactorySpiWrapper extends TrustManagerFactorySpi {
      private final TrustManager[] trustManagers;

      private TrustManagerFactorySpiWrapper(TrustManager trustManager) {
         Objects.requireNonNull(trustManager);
         this.trustManagers = new TrustManager[]{trustManager};
      }

      protected void engineInit(KeyStore keyStore) {
         TrustManagerFactoryWrapper.LOGGER.info("Ignoring provided KeyStore");
      }

      protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
         TrustManagerFactoryWrapper.LOGGER.info("Ignoring provided ManagerFactoryParameters");
      }

      protected TrustManager[] engineGetTrustManagers() {
         return this.trustManagers;
      }
   }
}
