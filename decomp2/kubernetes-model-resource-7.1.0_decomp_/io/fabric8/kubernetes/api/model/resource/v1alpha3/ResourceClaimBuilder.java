package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimBuilder extends ResourceClaimFluent implements VisitableBuilder {
   ResourceClaimFluent fluent;

   public ResourceClaimBuilder() {
      this(new ResourceClaim());
   }

   public ResourceClaimBuilder(ResourceClaimFluent fluent) {
      this(fluent, new ResourceClaim());
   }

   public ResourceClaimBuilder(ResourceClaimFluent fluent, ResourceClaim instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimBuilder(ResourceClaim instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaim build() {
      ResourceClaim buildable = new ResourceClaim(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
