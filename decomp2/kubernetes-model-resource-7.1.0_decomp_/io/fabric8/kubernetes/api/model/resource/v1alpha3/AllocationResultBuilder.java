package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllocationResultBuilder extends AllocationResultFluent implements VisitableBuilder {
   AllocationResultFluent fluent;

   public AllocationResultBuilder() {
      this(new AllocationResult());
   }

   public AllocationResultBuilder(AllocationResultFluent fluent) {
      this(fluent, new AllocationResult());
   }

   public AllocationResultBuilder(AllocationResultFluent fluent, AllocationResult instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllocationResultBuilder(AllocationResult instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllocationResult build() {
      AllocationResult buildable = new AllocationResult(this.fluent.buildDevices(), this.fluent.getNodeSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
