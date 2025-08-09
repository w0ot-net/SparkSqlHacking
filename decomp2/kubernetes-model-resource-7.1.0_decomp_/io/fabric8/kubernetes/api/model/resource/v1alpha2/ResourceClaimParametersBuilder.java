package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimParametersBuilder extends ResourceClaimParametersFluent implements VisitableBuilder {
   ResourceClaimParametersFluent fluent;

   public ResourceClaimParametersBuilder() {
      this(new ResourceClaimParameters());
   }

   public ResourceClaimParametersBuilder(ResourceClaimParametersFluent fluent) {
      this(fluent, new ResourceClaimParameters());
   }

   public ResourceClaimParametersBuilder(ResourceClaimParametersFluent fluent, ResourceClaimParameters instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimParametersBuilder(ResourceClaimParameters instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimParameters build() {
      ResourceClaimParameters buildable = new ResourceClaimParameters(this.fluent.getApiVersion(), this.fluent.buildDriverRequests(), this.fluent.buildGeneratedFrom(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getShareable());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
