package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScopedResourceSelectorRequirementBuilder extends ScopedResourceSelectorRequirementFluent implements VisitableBuilder {
   ScopedResourceSelectorRequirementFluent fluent;

   public ScopedResourceSelectorRequirementBuilder() {
      this(new ScopedResourceSelectorRequirement());
   }

   public ScopedResourceSelectorRequirementBuilder(ScopedResourceSelectorRequirementFluent fluent) {
      this(fluent, new ScopedResourceSelectorRequirement());
   }

   public ScopedResourceSelectorRequirementBuilder(ScopedResourceSelectorRequirementFluent fluent, ScopedResourceSelectorRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScopedResourceSelectorRequirementBuilder(ScopedResourceSelectorRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScopedResourceSelectorRequirement build() {
      ScopedResourceSelectorRequirement buildable = new ScopedResourceSelectorRequirement(this.fluent.getOperator(), this.fluent.getScopeName(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
