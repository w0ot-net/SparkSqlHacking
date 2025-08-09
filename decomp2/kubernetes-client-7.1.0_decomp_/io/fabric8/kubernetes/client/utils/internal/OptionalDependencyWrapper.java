package io.fabric8.kubernetes.client.utils.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import java.util.function.Supplier;

public class OptionalDependencyWrapper {
   private OptionalDependencyWrapper() {
   }

   public static Object wrapRunWithOptionalDependency(Supplier supplier, String message) {
      try {
         return supplier.get();
      } catch (NoClassDefFoundError ex) {
         throw new KubernetesClientException(String.format("%s, an optional dependency. To use this functionality you must explicitly add this dependency to the classpath.", message), ex);
      }
   }
}
