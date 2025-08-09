package org.sparkproject.jetty.util.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CRL;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Objects;
import org.sparkproject.jetty.util.resource.Resource;

public class CertificateUtils {
   public static KeyStore getKeyStore(Resource store, String storeType, String storeProvider, String storePassword) throws Exception {
      KeyStore keystore = null;
      if (store != null) {
         Objects.requireNonNull(storeType, "storeType cannot be null");
         if (storeProvider != null) {
            keystore = KeyStore.getInstance(storeType, storeProvider);
         } else {
            keystore = KeyStore.getInstance(storeType);
         }

         if (!store.exists()) {
            throw new IllegalStateException(store.getName() + " is not a valid keystore");
         }

         InputStream inStream = store.getInputStream();

         try {
            keystore.load(inStream, storePassword == null ? null : storePassword.toCharArray());
         } catch (Throwable var9) {
            if (inStream != null) {
               try {
                  inStream.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (inStream != null) {
            inStream.close();
         }
      }

      return keystore;
   }

   public static Collection loadCRL(String crlPath) throws Exception {
      Collection<? extends CRL> crlList = null;
      if (crlPath != null) {
         InputStream in = null;

         try {
            in = Resource.newResource(crlPath).getInputStream();
            crlList = CertificateFactory.getInstance("X.509").generateCRLs(in);
         } finally {
            if (in != null) {
               in.close();
            }

         }
      }

      return crlList;
   }
}
