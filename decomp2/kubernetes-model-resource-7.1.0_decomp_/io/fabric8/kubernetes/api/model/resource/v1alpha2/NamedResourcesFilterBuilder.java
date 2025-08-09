package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesFilterBuilder extends NamedResourcesFilterFluent implements VisitableBuilder {
   NamedResourcesFilterFluent fluent;

   public NamedResourcesFilterBuilder() {
      this(new NamedResourcesFilter());
   }

   public NamedResourcesFilterBuilder(NamedResourcesFilterFluent fluent) {
      this(fluent, new NamedResourcesFilter());
   }

   public NamedResourcesFilterBuilder(NamedResourcesFilterFluent fluent, NamedResourcesFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesFilterBuilder(NamedResourcesFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesFilter build() {
      NamedResourcesFilter buildable = new NamedResourcesFilter(this.fluent.getSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
