package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodResourceClaimStatusBuilder extends PodResourceClaimStatusFluent implements VisitableBuilder {
   PodResourceClaimStatusFluent fluent;

   public PodResourceClaimStatusBuilder() {
      this(new PodResourceClaimStatus());
   }

   public PodResourceClaimStatusBuilder(PodResourceClaimStatusFluent fluent) {
      this(fluent, new PodResourceClaimStatus());
   }

   public PodResourceClaimStatusBuilder(PodResourceClaimStatusFluent fluent, PodResourceClaimStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodResourceClaimStatusBuilder(PodResourceClaimStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodResourceClaimStatus build() {
      PodResourceClaimStatus buildable = new PodResourceClaimStatus(this.fluent.getName(), this.fluent.getResourceClaimName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
