package org.apache.commons.crypto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;

public final class Utils {
   private static final String SYSTEM_PROPERTIES_FILE = "commons.crypto.properties";

   private Utils() {
   }

   private static Properties createDefaultProperties() {
      Properties defaultedProps = new Properties(System.getProperties());

      try {
         Properties fileProps = new Properties();
         InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("commons.crypto.properties");
         Throwable var3 = null;

         try {
            if (is == null) {
               Properties var4 = defaultedProps;
               return var4;
            }

            fileProps.load(is);
         } catch (Throwable var15) {
            var3 = var15;
            throw var15;
         } finally {
            if (is != null) {
               if (var3 != null) {
                  try {
                     is.close();
                  } catch (Throwable var14) {
                     var3.addSuppressed(var14);
                  }
               } else {
                  is.close();
               }
            }

         }

         Enumeration<?> names = fileProps.propertyNames();

         while(names.hasMoreElements()) {
            String name = (String)names.nextElement();
            if (System.getProperty(name) == null) {
               defaultedProps.setProperty(name, fileProps.getProperty(name));
            }
         }

         return defaultedProps;
      } catch (Exception ex) {
         System.err.println("Could not load 'commons.crypto.properties' from classpath: " + ex.toString());
         return defaultedProps;
      }
   }

   public static Properties getDefaultProperties() {
      return new Properties(Utils.DefaultPropertiesHolder.DEFAULT_PROPERTIES);
   }

   public static Properties getProperties(Properties newProp) {
      Properties properties = new Properties(Utils.DefaultPropertiesHolder.DEFAULT_PROPERTIES);
      properties.putAll(newProp);
      return properties;
   }

   public static CryptoCipher getCipherInstance(String transformation, Properties properties) throws IOException {
      try {
         return CryptoCipherFactory.getCryptoCipher(transformation, properties);
      } catch (GeneralSecurityException e) {
         throw new IOException(e);
      }
   }

   public static void checkArgument(boolean expression) {
      if (!expression) {
         throw new IllegalArgumentException();
      }
   }

   public static void checkArgument(boolean expression, Object errorMessage) {
      if (!expression) {
         throw new IllegalArgumentException(String.valueOf(errorMessage));
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object checkNotNull(Object reference) {
      return Objects.requireNonNull(reference, "reference");
   }

   public static void checkState(boolean expression) {
      checkState(expression, (String)null);
   }

   public static void checkState(boolean expression, String message) {
      if (!expression) {
         throw new IllegalStateException(message);
      }
   }

   public static List splitClassNames(String clazzNames, String separator) {
      List<String> res = new ArrayList();
      if (clazzNames != null && !clazzNames.isEmpty()) {
         for(String clazzName : clazzNames.split(separator)) {
            clazzName = clazzName.trim();
            if (!clazzName.isEmpty()) {
               res.add(clazzName);
            }
         }

         return res;
      } else {
         return res;
      }
   }

   private static class DefaultPropertiesHolder {
      static final Properties DEFAULT_PROPERTIES = Utils.createDefaultProperties();
   }
}
