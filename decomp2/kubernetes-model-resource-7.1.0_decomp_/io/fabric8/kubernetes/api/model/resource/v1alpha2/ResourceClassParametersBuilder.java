package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClassParametersBuilder extends ResourceClassParametersFluent implements VisitableBuilder {
   ResourceClassParametersFluent fluent;

   public ResourceClassParametersBuilder() {
      this(new ResourceClassParameters());
   }

   public ResourceClassParametersBuilder(ResourceClassParametersFluent fluent) {
      this(fluent, new ResourceClassParameters());
   }

   public ResourceClassParametersBuilder(ResourceClassParametersFluent fluent, ResourceClassParameters instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClassParametersBuilder(ResourceClassParameters instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClassParameters build() {
      ResourceClassParameters buildable = new ResourceClassParameters(this.fluent.getApiVersion(), this.fluent.buildFilters(), this.fluent.buildGeneratedFrom(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildVendorParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
