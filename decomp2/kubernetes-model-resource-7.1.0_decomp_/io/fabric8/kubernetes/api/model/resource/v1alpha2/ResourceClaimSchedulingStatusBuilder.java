package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimSchedulingStatusBuilder extends ResourceClaimSchedulingStatusFluent implements VisitableBuilder {
   ResourceClaimSchedulingStatusFluent fluent;

   public ResourceClaimSchedulingStatusBuilder() {
      this(new ResourceClaimSchedulingStatus());
   }

   public ResourceClaimSchedulingStatusBuilder(ResourceClaimSchedulingStatusFluent fluent) {
      this(fluent, new ResourceClaimSchedulingStatus());
   }

   public ResourceClaimSchedulingStatusBuilder(ResourceClaimSchedulingStatusFluent fluent, ResourceClaimSchedulingStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimSchedulingStatusBuilder(ResourceClaimSchedulingStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimSchedulingStatus build() {
      ResourceClaimSchedulingStatus buildable = new ResourceClaimSchedulingStatus(this.fluent.getName(), this.fluent.getUnsuitableNodes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
