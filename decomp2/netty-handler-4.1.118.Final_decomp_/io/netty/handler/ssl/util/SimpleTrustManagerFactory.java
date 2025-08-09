package io.netty.handler.ssl.util;

import [Ljavax.net.ssl.TrustManager;;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

public abstract class SimpleTrustManagerFactory extends TrustManagerFactory {
   private static final Provider PROVIDER = new Provider("", (double)0.0F, "") {
      private static final long serialVersionUID = -2680540247105807895L;
   };
   private static final FastThreadLocal CURRENT_SPI = new FastThreadLocal() {
      protected SimpleTrustManagerFactorySpi initialValue() {
         return new SimpleTrustManagerFactorySpi();
      }
   };

   protected SimpleTrustManagerFactory() {
      this("");
   }

   protected SimpleTrustManagerFactory(String name) {
      super((TrustManagerFactorySpi)CURRENT_SPI.get(), PROVIDER, name);
      ((SimpleTrustManagerFactorySpi)CURRENT_SPI.get()).init(this);
      CURRENT_SPI.remove();
      ObjectUtil.checkNotNull(name, "name");
   }

   protected abstract void engineInit(KeyStore var1) throws Exception;

   protected abstract void engineInit(ManagerFactoryParameters var1) throws Exception;

   protected abstract TrustManager[] engineGetTrustManagers();

   static final class SimpleTrustManagerFactorySpi extends TrustManagerFactorySpi {
      private SimpleTrustManagerFactory parent;
      private volatile TrustManager[] trustManagers;

      void init(SimpleTrustManagerFactory parent) {
         this.parent = parent;
      }

      protected void engineInit(KeyStore keyStore) throws KeyStoreException {
         try {
            this.parent.engineInit(keyStore);
         } catch (KeyStoreException e) {
            throw e;
         } catch (Exception e) {
            throw new KeyStoreException(e);
         }
      }

      protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
         try {
            this.parent.engineInit(managerFactoryParameters);
         } catch (InvalidAlgorithmParameterException e) {
            throw e;
         } catch (Exception e) {
            throw new InvalidAlgorithmParameterException(e);
         }
      }

      protected TrustManager[] engineGetTrustManagers() {
         TrustManager[] trustManagers = this.trustManagers;
         if (trustManagers == null) {
            trustManagers = this.parent.engineGetTrustManagers();
            if (PlatformDependent.javaVersion() >= 7) {
               wrapIfNeeded(trustManagers);
            }

            this.trustManagers = trustManagers;
         }

         return (TrustManager[])((TrustManager;)trustManagers).clone();
      }

      @SuppressJava6Requirement(
         reason = "Usage guarded by java version check"
      )
      private static void wrapIfNeeded(TrustManager[] trustManagers) {
         for(int i = 0; i < trustManagers.length; ++i) {
            TrustManager tm = trustManagers[i];
            if (tm instanceof X509TrustManager && !(tm instanceof X509ExtendedTrustManager)) {
               trustManagers[i] = new X509TrustManagerWrapper((X509TrustManager)tm);
            }
         }

      }
   }
}
