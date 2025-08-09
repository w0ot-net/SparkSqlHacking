package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public interface Informable {
   /** @deprecated */
   @Deprecated
   Informable withIndexers(Map var1);

   Informable withLimit(Long var1);

   default SharedIndexInformer inform() {
      return this.inform((ResourceEventHandler)null, 0L);
   }

   default SharedIndexInformer inform(ResourceEventHandler handler) {
      return this.inform(handler, 0L);
   }

   SharedIndexInformer inform(ResourceEventHandler var1, long var2);

   SharedIndexInformer runnableInformer(long var1);

   CompletableFuture informOnCondition(Predicate var1);
}
