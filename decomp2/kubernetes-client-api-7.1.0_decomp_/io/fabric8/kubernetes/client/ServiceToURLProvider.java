package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.Service;

public interface ServiceToURLProvider {
   int getPriority();

   String getURL(Service var1, String var2, String var3, KubernetesClient var4);

   public static enum ServiceToUrlImplPriority {
      FIRST(0),
      SECOND(1),
      THIRD(2),
      FOURTH(3),
      FIFTH(4);

      private final int value;

      private ServiceToUrlImplPriority(int newVal) {
         this.value = newVal;
      }

      public int getValue() {
         return this.value;
      }
   }
}
