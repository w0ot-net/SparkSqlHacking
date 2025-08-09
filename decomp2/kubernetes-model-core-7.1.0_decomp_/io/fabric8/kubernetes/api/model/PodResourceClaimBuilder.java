package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodResourceClaimBuilder extends PodResourceClaimFluent implements VisitableBuilder {
   PodResourceClaimFluent fluent;

   public PodResourceClaimBuilder() {
      this(new PodResourceClaim());
   }

   public PodResourceClaimBuilder(PodResourceClaimFluent fluent) {
      this(fluent, new PodResourceClaim());
   }

   public PodResourceClaimBuilder(PodResourceClaimFluent fluent, PodResourceClaim instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodResourceClaimBuilder(PodResourceClaim instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodResourceClaim build() {
      PodResourceClaim buildable = new PodResourceClaim(this.fluent.getName(), this.fluent.getResourceClaimName(), this.fluent.getResourceClaimTemplateName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
