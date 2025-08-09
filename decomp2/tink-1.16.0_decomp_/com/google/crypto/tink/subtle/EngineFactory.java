package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public final class EngineFactory {
   private final Policy policy;
   public static final EngineFactory CIPHER = new EngineFactory(new EngineWrapper.TCipher());
   public static final EngineFactory MAC = new EngineFactory(new EngineWrapper.TMac());
   public static final EngineFactory SIGNATURE = new EngineFactory(new EngineWrapper.TSignature());
   public static final EngineFactory MESSAGE_DIGEST = new EngineFactory(new EngineWrapper.TMessageDigest());
   public static final EngineFactory KEY_AGREEMENT = new EngineFactory(new EngineWrapper.TKeyAgreement());
   public static final EngineFactory KEY_PAIR_GENERATOR = new EngineFactory(new EngineWrapper.TKeyPairGenerator());
   public static final EngineFactory KEY_FACTORY = new EngineFactory(new EngineWrapper.TKeyFactory());

   public static List toProviderList(String... providerNames) {
      List<Provider> providers = new ArrayList();

      for(String s : providerNames) {
         Provider p = Security.getProvider(s);
         if (p != null) {
            providers.add(p);
         }
      }

      return providers;
   }

   public EngineFactory(EngineWrapper instanceBuilder) {
      if (TinkFipsUtil.useOnlyFips()) {
         this.policy = new FipsPolicy(instanceBuilder);
      } else if (SubtleUtil.isAndroid()) {
         this.policy = new AndroidPolicy(instanceBuilder);
      } else {
         this.policy = new DefaultPolicy(instanceBuilder);
      }

   }

   public Object getInstance(String algorithm) throws GeneralSecurityException {
      return this.policy.getInstance(algorithm);
   }

   Object getInstance(String algorithm, List preferredProviders) throws GeneralSecurityException {
      return this.policy.getInstance(algorithm, preferredProviders);
   }

   private static class DefaultPolicy implements Policy {
      private final EngineWrapper jceFactory;

      private DefaultPolicy(EngineWrapper jceFactory) {
         this.jceFactory = jceFactory;
      }

      public Object getInstance(String algorithm) throws GeneralSecurityException {
         return this.jceFactory.getInstance(algorithm, (Provider)null);
      }

      public Object getInstance(String algorithm, List preferredProviders) throws GeneralSecurityException {
         for(Provider provider : preferredProviders) {
            try {
               return this.jceFactory.getInstance(algorithm, provider);
            } catch (Exception var6) {
            }
         }

         return this.getInstance(algorithm);
      }
   }

   private static class FipsPolicy implements Policy {
      private final EngineWrapper jceFactory;

      private FipsPolicy(EngineWrapper jceFactory) {
         this.jceFactory = jceFactory;
      }

      public Object getInstance(String algorithm) throws GeneralSecurityException {
         List<Provider> conscryptProviders = EngineFactory.toProviderList("GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt");
         Exception cause = null;

         for(Provider provider : conscryptProviders) {
            try {
               return this.jceFactory.getInstance(algorithm, provider);
            } catch (Exception e) {
               if (cause == null) {
                  cause = e;
               }
            }
         }

         throw new GeneralSecurityException("No good Provider found.", cause);
      }

      public Object getInstance(String algorithm, List preferredProviders) throws GeneralSecurityException {
         return this.getInstance(algorithm);
      }
   }

   private static class AndroidPolicy implements Policy {
      private final EngineWrapper jceFactory;

      private AndroidPolicy(EngineWrapper jceFactory) {
         this.jceFactory = jceFactory;
      }

      public Object getInstance(String algorithm) throws GeneralSecurityException {
         List<Provider> conscryptProviders = EngineFactory.toProviderList("GmsCore_OpenSSL", "AndroidOpenSSL");
         Exception cause = null;

         for(Provider provider : conscryptProviders) {
            try {
               return this.jceFactory.getInstance(algorithm, provider);
            } catch (Exception e) {
               if (cause == null) {
                  cause = e;
               }
            }
         }

         return this.jceFactory.getInstance(algorithm, (Provider)null);
      }

      public Object getInstance(String algorithm, List preferredProviders) throws GeneralSecurityException {
         return this.getInstance(algorithm);
      }
   }

   private interface Policy {
      Object getInstance(String algorithm) throws GeneralSecurityException;

      Object getInstance(String algorithm, List preferredProviders) throws GeneralSecurityException;
   }
}
