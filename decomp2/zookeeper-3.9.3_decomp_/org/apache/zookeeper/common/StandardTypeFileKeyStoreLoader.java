package org.apache.zookeeper.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;

abstract class StandardTypeFileKeyStoreLoader extends FileKeyStoreLoader {
   private static final char[] EMPTY_CHAR_ARRAY = new char[0];
   protected final SupportedStandardKeyFormat format;

   StandardTypeFileKeyStoreLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword, SupportedStandardKeyFormat format) {
      super(keyStorePath, trustStorePath, keyStorePassword, trustStorePassword);
      this.format = format;
   }

   public KeyStore loadKeyStore() throws IOException, GeneralSecurityException {
      InputStream inputStream = new FileInputStream(new File(this.keyStorePath));

      KeyStore var3;
      try {
         KeyStore ks = this.keyStoreInstance();
         ks.load(inputStream, passwordStringToCharArray(this.keyStorePassword));
         var3 = ks;
      } catch (Throwable var5) {
         try {
            inputStream.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      inputStream.close();
      return var3;
   }

   public KeyStore loadTrustStore() throws IOException, GeneralSecurityException {
      InputStream inputStream = new FileInputStream(new File(this.trustStorePath));

      KeyStore var3;
      try {
         KeyStore ts = this.keyStoreInstance();
         ts.load(inputStream, passwordStringToCharArray(this.trustStorePassword));
         var3 = ts;
      } catch (Throwable var5) {
         try {
            inputStream.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      inputStream.close();
      return var3;
   }

   private KeyStore keyStoreInstance() throws KeyStoreException {
      return KeyStore.getInstance(this.format.name());
   }

   private static char[] passwordStringToCharArray(String password) {
      return password == null ? EMPTY_CHAR_ARRAY : password.toCharArray();
   }

   protected static enum SupportedStandardKeyFormat {
      JKS,
      PKCS12,
      BCFKS;
   }
}
