package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourcePoolBuilder extends ResourcePoolFluent implements VisitableBuilder {
   ResourcePoolFluent fluent;

   public ResourcePoolBuilder() {
      this(new ResourcePool());
   }

   public ResourcePoolBuilder(ResourcePoolFluent fluent) {
      this(fluent, new ResourcePool());
   }

   public ResourcePoolBuilder(ResourcePoolFluent fluent, ResourcePool instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourcePoolBuilder(ResourcePool instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourcePool build() {
      ResourcePool buildable = new ResourcePool(this.fluent.getGeneration(), this.fluent.getName(), this.fluent.getResourceSliceCount());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
