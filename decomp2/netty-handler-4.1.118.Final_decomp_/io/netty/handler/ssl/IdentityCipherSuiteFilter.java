package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class IdentityCipherSuiteFilter implements CipherSuiteFilter {
   public static final IdentityCipherSuiteFilter INSTANCE = new IdentityCipherSuiteFilter(true);
   public static final IdentityCipherSuiteFilter INSTANCE_DEFAULTING_TO_SUPPORTED_CIPHERS = new IdentityCipherSuiteFilter(false);
   private final boolean defaultToDefaultCiphers;

   private IdentityCipherSuiteFilter(boolean defaultToDefaultCiphers) {
      this.defaultToDefaultCiphers = defaultToDefaultCiphers;
   }

   public String[] filterCipherSuites(Iterable ciphers, List defaultCiphers, Set supportedCiphers) {
      if (ciphers == null) {
         return this.defaultToDefaultCiphers ? (String[])defaultCiphers.toArray(EmptyArrays.EMPTY_STRINGS) : (String[])supportedCiphers.toArray(EmptyArrays.EMPTY_STRINGS);
      } else {
         List<String> newCiphers = new ArrayList(supportedCiphers.size());

         for(String c : ciphers) {
            if (c == null) {
               break;
            }

            newCiphers.add(c);
         }

         return (String[])newCiphers.toArray(EmptyArrays.EMPTY_STRINGS);
      }
   }
}
