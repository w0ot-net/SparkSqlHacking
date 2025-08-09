package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.GenericKubernetesResourceList;

public class GenericKubernetesResourceOperationsImpl extends HasMetadataOperation {
   private final boolean resourceNamespaced;

   public GenericKubernetesResourceOperationsImpl(OperationContext context, boolean resourceNamespaced) {
      super(context, GenericKubernetesResource.class, GenericKubernetesResourceList.class);
      this.resourceNamespaced = resourceNamespaced;
   }

   public HasMetadataOperation newInstance(OperationContext context) {
      return new GenericKubernetesResourceOperationsImpl(context, this.resourceNamespaced);
   }

   public boolean isResourceNamespaced() {
      return this.resourceNamespaced;
   }
}
