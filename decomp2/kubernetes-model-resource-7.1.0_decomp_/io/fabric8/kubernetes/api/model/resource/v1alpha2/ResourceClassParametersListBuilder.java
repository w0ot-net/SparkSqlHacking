package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClassParametersListBuilder extends ResourceClassParametersListFluent implements VisitableBuilder {
   ResourceClassParametersListFluent fluent;

   public ResourceClassParametersListBuilder() {
      this(new ResourceClassParametersList());
   }

   public ResourceClassParametersListBuilder(ResourceClassParametersListFluent fluent) {
      this(fluent, new ResourceClassParametersList());
   }

   public ResourceClassParametersListBuilder(ResourceClassParametersListFluent fluent, ResourceClassParametersList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClassParametersListBuilder(ResourceClassParametersList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClassParametersList build() {
      ResourceClassParametersList buildable = new ResourceClassParametersList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
