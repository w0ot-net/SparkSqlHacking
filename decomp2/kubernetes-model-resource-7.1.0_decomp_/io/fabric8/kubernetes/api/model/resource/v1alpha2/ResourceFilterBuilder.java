package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceFilterBuilder extends ResourceFilterFluent implements VisitableBuilder {
   ResourceFilterFluent fluent;

   public ResourceFilterBuilder() {
      this(new ResourceFilter());
   }

   public ResourceFilterBuilder(ResourceFilterFluent fluent) {
      this(fluent, new ResourceFilter());
   }

   public ResourceFilterBuilder(ResourceFilterFluent fluent, ResourceFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceFilterBuilder(ResourceFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceFilter build() {
      ResourceFilter buildable = new ResourceFilter(this.fluent.getDriverName(), this.fluent.buildNamedResources());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
