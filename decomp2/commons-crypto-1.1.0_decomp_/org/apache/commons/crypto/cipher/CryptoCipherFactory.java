package org.apache.commons.crypto.cipher;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import org.apache.commons.crypto.utils.ReflectionUtils;
import org.apache.commons.crypto.utils.Utils;

public class CryptoCipherFactory {
   public static final String JCE_PROVIDER_KEY = "commons.crypto.cipher.jce.provider";
   public static final String CLASSES_KEY = "commons.crypto.cipher.classes";
   public static final int AES_BLOCK_SIZE = 16;
   private static final String CLASSES_DEFAULT;

   private CryptoCipherFactory() {
   }

   public static CryptoCipher getCryptoCipher(String transformation, Properties properties) throws GeneralSecurityException {
      List<String> names = Utils.splitClassNames(getCipherClassString(properties), ",");
      if (names.size() == 0) {
         throw new IllegalArgumentException("No classname(s) provided");
      } else {
         CryptoCipher cipher = null;
         Exception lastException = null;
         StringBuilder errorMessage = new StringBuilder("CryptoCipher ");

         for(String klass : names) {
            try {
               Class<?> cls = ReflectionUtils.getClassByName(klass);
               cipher = (CryptoCipher)ReflectionUtils.newInstance(cls.asSubclass(CryptoCipher.class), properties, transformation);
               if (cipher != null) {
                  break;
               }
            } catch (Exception e) {
               lastException = e;
               errorMessage.append("{" + klass + "}");
            }
         }

         if (cipher != null) {
            return cipher;
         } else {
            errorMessage.append(" is not available or transformation " + transformation + " is not supported.");
            throw new GeneralSecurityException(errorMessage.toString(), lastException);
         }
      }
   }

   public static CryptoCipher getCryptoCipher(String transformation) throws GeneralSecurityException {
      return getCryptoCipher(transformation, new Properties());
   }

   private static String getCipherClassString(Properties props) {
      String cipherClassString = props.getProperty("commons.crypto.cipher.classes", CLASSES_DEFAULT);
      if (cipherClassString.isEmpty()) {
         cipherClassString = CLASSES_DEFAULT;
      }

      return cipherClassString;
   }

   static {
      CLASSES_DEFAULT = CryptoCipherFactory.CipherProvider.OPENSSL.getClassName().concat(",").concat(CryptoCipherFactory.CipherProvider.JCE.getClassName());
   }

   public static enum CipherProvider {
      OPENSSL(OpenSslCipher.class),
      JCE(JceCipher.class);

      private final Class klass;
      private final String className;

      private CipherProvider(Class klass) {
         this.klass = klass;
         this.className = klass.getName();
      }

      public String getClassName() {
         return this.className;
      }

      public Class getImplClass() {
         return this.klass;
      }
   }
}
