package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CreateOnlyResourceOperation extends OperationSupport implements InOutCreateable {
   protected Class type;

   protected CreateOnlyResourceOperation(OperationContext ctx) {
      super(ctx);
   }

   public Class getType() {
      return this.type;
   }

   protected Object handleCreate(Object resource) throws ExecutionException, InterruptedException, IOException {
      return this.handleCreate(resource, this.getType());
   }

   public Object create(Object item) {
      try {
         return this.handleCreate(item);
      } catch (IOException | ExecutionException e) {
         throw KubernetesClientException.launderThrowable(e);
      } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(ie);
      }
   }
}
