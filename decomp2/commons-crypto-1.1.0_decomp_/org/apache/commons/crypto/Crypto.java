package org.apache.commons.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.random.CryptoRandom;
import org.apache.commons.crypto.random.CryptoRandomFactory;

public final class Crypto {
   public static final String CONF_PREFIX = "commons.crypto.";
   public static final String LIB_NAME_KEY = "commons.crypto.lib.name";
   public static final String LIB_PATH_KEY = "commons.crypto.lib.path";
   public static final String LIB_TEMPDIR_KEY = "commons.crypto.lib.tempdir";

   public static String getComponentName() {
      return Crypto.ComponentPropertiesHolder.PROPERTIES.getProperty("NAME");
   }

   public static String getComponentVersion() {
      return Crypto.ComponentPropertiesHolder.PROPERTIES.getProperty("VERSION");
   }

   public static Throwable getLoadingError() {
      return NativeCodeLoader.getLoadingError();
   }

   private static void info(String format, Object... args) {
      System.out.println(String.format(format, args));
   }

   public static boolean isNativeCodeLoaded() {
      return NativeCodeLoader.isNativeCodeLoaded();
   }

   public static void main(String[] args) throws Exception {
      info("%s %s", getComponentName(), getComponentVersion());
      if (isNativeCodeLoaded()) {
         info("Native code loaded OK: %s", OpenSslInfoNative.NativeVersion());
         info("Native name: %s", OpenSslInfoNative.NativeName());
         info("Native built: %s", OpenSslInfoNative.NativeTimeStamp());
         info("OpenSSL library loaded OK, version: 0x%s", Long.toHexString(OpenSslInfoNative.OpenSSL()));
         info("OpenSSL library info: %s", OpenSslInfoNative.OpenSSLVersion(0));
         Properties props = new Properties();
         props.setProperty("commons.crypto.secure.random.classes", CryptoRandomFactory.RandomProvider.OPENSSL.getClassName());
         CryptoRandom cryptoRandom = CryptoRandomFactory.getCryptoRandom(props);
         Throwable var3 = null;

         try {
            info("Random instance created OK: %s", cryptoRandom);
         } catch (Throwable var27) {
            var3 = var27;
            throw var27;
         } finally {
            if (cryptoRandom != null) {
               if (var3 != null) {
                  try {
                     cryptoRandom.close();
                  } catch (Throwable var25) {
                     var3.addSuppressed(var25);
                  }
               } else {
                  cryptoRandom.close();
               }
            }

         }

         props = new Properties();
         props.setProperty("commons.crypto.cipher.classes", CryptoCipherFactory.CipherProvider.OPENSSL.getClassName());
         String transformation = "AES/CTR/NoPadding";
         CryptoCipher cryptoCipher = CryptoCipherFactory.getCryptoCipher("AES/CTR/NoPadding", props);
         Throwable var4 = null;

         try {
            info("Cipher %s instance created OK: %s", "AES/CTR/NoPadding", cryptoCipher);
         } catch (Throwable var26) {
            var4 = var26;
            throw var26;
         } finally {
            if (cryptoCipher != null) {
               if (var4 != null) {
                  try {
                     cryptoCipher.close();
                  } catch (Throwable var24) {
                     var4.addSuppressed(var24);
                  }
               } else {
                  cryptoCipher.close();
               }
            }

         }

         info("Additional OpenSSL_version(n) details:");

         for(int j = 1; j < 6; ++j) {
            info("%s: %s", j, OpenSslInfoNative.OpenSSLVersion(j));
         }
      } else {
         info("Native load failed: %s", getLoadingError());
      }

   }

   private static class ComponentPropertiesHolder {
      static final Properties PROPERTIES = getComponentProperties();

      private static Properties getComponentProperties() {
         URL url = Crypto.class.getResource("/org/apache/commons/crypto/component.properties");
         Properties versionData = new Properties();
         if (url != null) {
            try {
               InputStream inputStream = url.openStream();
               Throwable var3 = null;

               Properties var4;
               try {
                  versionData.load(inputStream);
                  var4 = versionData;
               } catch (Throwable var14) {
                  var3 = var14;
                  throw var14;
               } finally {
                  if (inputStream != null) {
                     if (var3 != null) {
                        try {
                           inputStream.close();
                        } catch (Throwable var13) {
                           var3.addSuppressed(var13);
                        }
                     } else {
                        inputStream.close();
                     }
                  }

               }

               return var4;
            } catch (IOException var16) {
            }
         }

         return versionData;
      }
   }
}
