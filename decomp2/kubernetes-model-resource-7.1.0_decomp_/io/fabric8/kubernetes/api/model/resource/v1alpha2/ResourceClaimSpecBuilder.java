package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimSpecBuilder extends ResourceClaimSpecFluent implements VisitableBuilder {
   ResourceClaimSpecFluent fluent;

   public ResourceClaimSpecBuilder() {
      this(new ResourceClaimSpec());
   }

   public ResourceClaimSpecBuilder(ResourceClaimSpecFluent fluent) {
      this(fluent, new ResourceClaimSpec());
   }

   public ResourceClaimSpecBuilder(ResourceClaimSpecFluent fluent, ResourceClaimSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimSpecBuilder(ResourceClaimSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimSpec build() {
      ResourceClaimSpec buildable = new ResourceClaimSpec(this.fluent.getAllocationMode(), this.fluent.buildParametersRef(), this.fluent.getResourceClassName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
