package io.fabric8.kubernetes.client.dsl.internal.apps.v1;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.TimeoutImageEditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

public abstract class RollableScalableResourceOperation extends HasMetadataOperation implements RollableScalableResource, TimeoutImageEditReplacePatchable {
   protected final PodOperationContext rollingOperationContext;

   protected RollableScalableResourceOperation(PodOperationContext context, OperationContext superContext, Class type, Class listType) {
      super(superContext, type, listType);
      this.rollingOperationContext = context;
   }

   protected abstract RollingUpdater getRollingUpdater(long var1, TimeUnit var3);

   public HasMetadata edit(UnaryOperator function) {
      RollingUpdater<T, L> rollingUpdater = this.getRollingUpdater(this.context.getTimeout(), this.context.getTimeoutUnit());
      if (this.rollingOperationContext.isRolling() && rollingUpdater != null) {
         T oldObj = (T)this.getItemOrRequireFromServer();
         T newObj = (T)((HasMetadata)function.apply((HasMetadata)this.getKubernetesSerialization().clone(oldObj)));
         return rollingUpdater.rollUpdate(oldObj, newObj);
      } else {
         return super.edit(function);
      }
   }

   public abstract RollableScalableResourceOperation newInstance(PodOperationContext var1, OperationContext var2);

   public Loggable withLogWaitTimeout(Integer logWaitTimeout) {
      return this.withReadyWaitTimeout(logWaitTimeout);
   }

   public Loggable withReadyWaitTimeout(Integer timeout) {
      return this.newInstance(this.rollingOperationContext.withReadyWaitTimeout(timeout), this.context);
   }

   public Loggable inContainer(String id) {
      return this.newInstance(this.rollingOperationContext.withContainerId(id), this.context);
   }

   public TimeoutImageEditReplacePatchable rolling() {
      return this.newInstance(this.rollingOperationContext.toBuilder().rolling(true).build(), this.context);
   }

   public String getLog() {
      return this.getLog(this.rollingOperationContext.isPrettyOutput());
   }

   public LogWatch watchLog() {
      return this.watchLog((OutputStream)null);
   }

   public HasMetadata updateImage(String image) {
      T value = (T)this.get();
      if (value == null) {
         throw new KubernetesClientException("Resource doesn't exist");
      } else {
         List<Container> containers = this.getContainers(value);
         if (containers.size() > 1) {
            throw new KubernetesClientException("Image update is not supported for multicontainer pods");
         } else if (containers.isEmpty()) {
            throw new KubernetesClientException("Pod has no containers!");
         } else {
            Container container = (Container)containers.iterator().next();
            return this.updateImage(Collections.singletonMap(container.getName(), image));
         }
      }
   }

   protected abstract List getContainers(HasMetadata var1);

   public HasMetadata updateImage(Map containerToImageMap) {
      T value = (T)this.get();
      if (value == null) {
         throw new KubernetesClientException("Resource doesn't exist");
      } else {
         T base = (T)((HasMetadata)this.getKubernetesSerialization().clone(value));
         List<Container> containers = this.getContainers(value);
         if (containers.isEmpty()) {
            throw new KubernetesClientException("Pod has no containers!");
         } else {
            for(Container container : containers) {
               if (containerToImageMap.containsKey(container.getName())) {
                  container.setImage((String)containerToImageMap.get(container.getName()));
               }
            }

            return this.sendPatchedObject(base, value);
         }
      }
   }

   protected HasMetadata sendPatchedObject(HasMetadata oldObject, HasMetadata updatedObject) {
      return this.patch((PatchContext)null, oldObject, updatedObject);
   }

   public RollableScalableResourceOperation withTimeout(long timeout, TimeUnit unit) {
      return this.newInstance(this.rollingOperationContext, this.context.withTimeout(timeout, unit));
   }

   public RollableScalableResourceOperation withTimeoutInMillis(long timeoutInMillis) {
      return this.withTimeout(timeoutInMillis, TimeUnit.MILLISECONDS);
   }

   public HasMetadata pause() {
      throw new KubernetesClientException(this.context.getPlural() + " pausing is not supported");
   }

   public HasMetadata resume() {
      throw new KubernetesClientException(this.context.getPlural() + " resuming is not supported");
   }

   public HasMetadata restart() {
      throw new KubernetesClientException(this.context.getPlural() + " restarting is not supported");
   }

   public HasMetadata undo() {
      throw new KubernetesClientException(this.context.getPlural() + " undo is not supported");
   }
}
