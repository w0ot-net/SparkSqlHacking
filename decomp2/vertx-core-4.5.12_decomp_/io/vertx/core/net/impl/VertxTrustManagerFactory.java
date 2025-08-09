package io.vertx.core.net.impl;

import [Ljavax.net.ssl.TrustManager;;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;

class VertxTrustManagerFactory extends TrustManagerFactory {
   private static final Provider PROVIDER = new Provider("", (double)0.0F, "") {
   };

   VertxTrustManagerFactory(final TrustManager... tm) {
      super(new TrustManagerFactorySpi() {
         protected void engineInit(KeyStore keyStore) throws KeyStoreException {
         }

         protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
         }

         protected TrustManager[] engineGetTrustManagers() {
            return (TrustManager[])((TrustManager;)tm).clone();
         }
      }, PROVIDER, "");
   }
}
