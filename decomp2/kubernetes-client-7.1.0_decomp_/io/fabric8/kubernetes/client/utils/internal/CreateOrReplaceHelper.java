package io.fabric8.kubernetes.client.utils.internal;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class CreateOrReplaceHelper {
   public static final int CREATE_OR_REPLACE_RETRIES = 3;
   private final UnaryOperator createTask;
   private final UnaryOperator replaceTask;
   private final UnaryOperator waitTask;
   private final UnaryOperator reloadTask;
   private final KubernetesSerialization serialization;

   public CreateOrReplaceHelper(UnaryOperator createTask, UnaryOperator replaceTask, UnaryOperator waitTask, UnaryOperator reloadTask, KubernetesSerialization serialization) {
      this.createTask = createTask;
      this.replaceTask = replaceTask;
      this.waitTask = waitTask;
      this.reloadTask = reloadTask;
      this.serialization = serialization;
   }

   public HasMetadata createOrReplace(HasMetadata item) {
      String resourceVersion = KubernetesResourceUtil.getResourceVersion(item);
      CompletableFuture<T> future = new CompletableFuture();
      int nTries = 0;
      item = (T)((HasMetadata)this.serialization.clone(item));

      while(!future.isDone() && nTries < 3) {
         try {
            KubernetesResourceUtil.setResourceVersion(item, (String)null);
            return (HasMetadata)this.createTask.apply(item);
         } catch (KubernetesClientException exception) {
            if (this.shouldRetry(exception.getCode())) {
               T itemFromServer = (T)((HasMetadata)this.reloadTask.apply(item));
               if (itemFromServer == null) {
                  this.waitTask.apply(item);
                  ++nTries;
                  continue;
               }
            } else if (exception.getCode() != 409) {
               throw exception;
            }

            future.complete(this.replace(item, resourceVersion));
         }
      }

      return (HasMetadata)future.join();
   }

   private HasMetadata replace(HasMetadata item, String resourceVersion) {
      KubernetesResourceUtil.setResourceVersion(item, resourceVersion);
      return (HasMetadata)this.replaceTask.apply(item);
   }

   private boolean shouldRetry(int responseCode) {
      return responseCode > 499;
   }
}
