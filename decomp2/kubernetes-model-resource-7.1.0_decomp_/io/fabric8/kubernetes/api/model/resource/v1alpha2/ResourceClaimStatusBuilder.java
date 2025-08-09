package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimStatusBuilder extends ResourceClaimStatusFluent implements VisitableBuilder {
   ResourceClaimStatusFluent fluent;

   public ResourceClaimStatusBuilder() {
      this(new ResourceClaimStatus());
   }

   public ResourceClaimStatusBuilder(ResourceClaimStatusFluent fluent) {
      this(fluent, new ResourceClaimStatus());
   }

   public ResourceClaimStatusBuilder(ResourceClaimStatusFluent fluent, ResourceClaimStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimStatusBuilder(ResourceClaimStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimStatus build() {
      ResourceClaimStatus buildable = new ResourceClaimStatus(this.fluent.buildAllocation(), this.fluent.getDeallocationRequested(), this.fluent.getDriverName(), this.fluent.buildReservedFor());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
