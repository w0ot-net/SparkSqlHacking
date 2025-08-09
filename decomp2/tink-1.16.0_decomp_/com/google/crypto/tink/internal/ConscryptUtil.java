package com.google.crypto.tink.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Provider;
import java.security.Security;
import javax.annotation.Nullable;

public final class ConscryptUtil {
   private static final String[] CONSCRYPT_PROVIDER_NAMES = new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"};

   @Nullable
   public static Provider providerOrNull() {
      for(String providerName : CONSCRYPT_PROVIDER_NAMES) {
         Provider provider = Security.getProvider(providerName);
         if (provider != null) {
            return provider;
         }
      }

      return null;
   }

   @Nullable
   public static Provider providerWithReflectionOrNull() {
      try {
         Class<?> conscrypt = Class.forName("org.conscrypt.Conscrypt");
         Method getProvider = conscrypt.getMethod("newProvider");
         return (Provider)getProvider.invoke((Object)null);
      } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException | ClassNotFoundException var2) {
         return null;
      }
   }

   public static final boolean isConscryptProvider(Provider provider) {
      String providerName = provider.getName();
      return providerName.equals("GmsCore_OpenSSL") || providerName.equals("AndroidOpenSSL") || providerName.equals("Conscrypt");
   }

   private ConscryptUtil() {
   }
}
