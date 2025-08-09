package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FieldSelectorRequirementBuilder extends FieldSelectorRequirementFluent implements VisitableBuilder {
   FieldSelectorRequirementFluent fluent;

   public FieldSelectorRequirementBuilder() {
      this(new FieldSelectorRequirement());
   }

   public FieldSelectorRequirementBuilder(FieldSelectorRequirementFluent fluent) {
      this(fluent, new FieldSelectorRequirement());
   }

   public FieldSelectorRequirementBuilder(FieldSelectorRequirementFluent fluent, FieldSelectorRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FieldSelectorRequirementBuilder(FieldSelectorRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FieldSelectorRequirement build() {
      FieldSelectorRequirement buildable = new FieldSelectorRequirement(this.fluent.getKey(), this.fluent.getOperator(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
