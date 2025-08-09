package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimParametersReferenceBuilder extends ResourceClaimParametersReferenceFluent implements VisitableBuilder {
   ResourceClaimParametersReferenceFluent fluent;

   public ResourceClaimParametersReferenceBuilder() {
      this(new ResourceClaimParametersReference());
   }

   public ResourceClaimParametersReferenceBuilder(ResourceClaimParametersReferenceFluent fluent) {
      this(fluent, new ResourceClaimParametersReference());
   }

   public ResourceClaimParametersReferenceBuilder(ResourceClaimParametersReferenceFluent fluent, ResourceClaimParametersReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimParametersReferenceBuilder(ResourceClaimParametersReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimParametersReference build() {
      ResourceClaimParametersReference buildable = new ResourceClaimParametersReference(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
