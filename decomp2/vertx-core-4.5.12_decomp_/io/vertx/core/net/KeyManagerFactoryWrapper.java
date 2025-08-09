package io.vertx.core.net;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KeyManagerFactoryWrapper extends KeyManagerFactory {
   private static final Logger LOGGER = LoggerFactory.getLogger(KeyManagerFactoryWrapper.class);
   private static final String KEY_MANAGER_FACTORY_ALGORITHM = "no-algorithm";
   private static final Provider PROVIDER = new Provider("", (double)1.0F, "") {
   };

   KeyManagerFactoryWrapper(KeyManager keyManager) {
      super(new KeyManagerFactorySpiWrapper(keyManager), PROVIDER, "no-algorithm");
   }

   private static class KeyManagerFactorySpiWrapper extends KeyManagerFactorySpi {
      private final KeyManager[] keyManagers;

      private KeyManagerFactorySpiWrapper(KeyManager keyManager) {
         Objects.requireNonNull(keyManager);
         this.keyManagers = new KeyManager[]{keyManager};
      }

      protected void engineInit(KeyStore keyStore, char[] keyStorePassword) {
         KeyManagerFactoryWrapper.LOGGER.info("Ignoring provided KeyStore");
      }

      protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
         KeyManagerFactoryWrapper.LOGGER.info("Ignoring provided ManagerFactoryParameters");
      }

      protected KeyManager[] engineGetKeyManagers() {
         return this.keyManagers;
      }
   }
}
