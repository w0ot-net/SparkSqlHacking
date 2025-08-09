package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TopologySelectorLabelRequirementBuilder extends TopologySelectorLabelRequirementFluent implements VisitableBuilder {
   TopologySelectorLabelRequirementFluent fluent;

   public TopologySelectorLabelRequirementBuilder() {
      this(new TopologySelectorLabelRequirement());
   }

   public TopologySelectorLabelRequirementBuilder(TopologySelectorLabelRequirementFluent fluent) {
      this(fluent, new TopologySelectorLabelRequirement());
   }

   public TopologySelectorLabelRequirementBuilder(TopologySelectorLabelRequirementFluent fluent, TopologySelectorLabelRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TopologySelectorLabelRequirementBuilder(TopologySelectorLabelRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TopologySelectorLabelRequirement build() {
      TopologySelectorLabelRequirement buildable = new TopologySelectorLabelRequirement(this.fluent.getKey(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
