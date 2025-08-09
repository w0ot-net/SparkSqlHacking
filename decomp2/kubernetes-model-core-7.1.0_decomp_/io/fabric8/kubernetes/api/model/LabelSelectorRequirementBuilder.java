package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LabelSelectorRequirementBuilder extends LabelSelectorRequirementFluent implements VisitableBuilder {
   LabelSelectorRequirementFluent fluent;

   public LabelSelectorRequirementBuilder() {
      this(new LabelSelectorRequirement());
   }

   public LabelSelectorRequirementBuilder(LabelSelectorRequirementFluent fluent) {
      this(fluent, new LabelSelectorRequirement());
   }

   public LabelSelectorRequirementBuilder(LabelSelectorRequirementFluent fluent, LabelSelectorRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LabelSelectorRequirementBuilder(LabelSelectorRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LabelSelectorRequirement build() {
      LabelSelectorRequirement buildable = new LabelSelectorRequirement(this.fluent.getKey(), this.fluent.getOperator(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
