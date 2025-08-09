package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimParametersListBuilder extends ResourceClaimParametersListFluent implements VisitableBuilder {
   ResourceClaimParametersListFluent fluent;

   public ResourceClaimParametersListBuilder() {
      this(new ResourceClaimParametersList());
   }

   public ResourceClaimParametersListBuilder(ResourceClaimParametersListFluent fluent) {
      this(fluent, new ResourceClaimParametersList());
   }

   public ResourceClaimParametersListBuilder(ResourceClaimParametersListFluent fluent, ResourceClaimParametersList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimParametersListBuilder(ResourceClaimParametersList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimParametersList build() {
      ResourceClaimParametersList buildable = new ResourceClaimParametersList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
