package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesAllocationResultBuilder extends NamedResourcesAllocationResultFluent implements VisitableBuilder {
   NamedResourcesAllocationResultFluent fluent;

   public NamedResourcesAllocationResultBuilder() {
      this(new NamedResourcesAllocationResult());
   }

   public NamedResourcesAllocationResultBuilder(NamedResourcesAllocationResultFluent fluent) {
      this(fluent, new NamedResourcesAllocationResult());
   }

   public NamedResourcesAllocationResultBuilder(NamedResourcesAllocationResultFluent fluent, NamedResourcesAllocationResult instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesAllocationResultBuilder(NamedResourcesAllocationResult instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesAllocationResult build() {
      NamedResourcesAllocationResult buildable = new NamedResourcesAllocationResult(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
