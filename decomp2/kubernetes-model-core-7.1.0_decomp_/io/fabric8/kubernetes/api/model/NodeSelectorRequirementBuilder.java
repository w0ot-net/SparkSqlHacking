package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeSelectorRequirementBuilder extends NodeSelectorRequirementFluent implements VisitableBuilder {
   NodeSelectorRequirementFluent fluent;

   public NodeSelectorRequirementBuilder() {
      this(new NodeSelectorRequirement());
   }

   public NodeSelectorRequirementBuilder(NodeSelectorRequirementFluent fluent) {
      this(fluent, new NodeSelectorRequirement());
   }

   public NodeSelectorRequirementBuilder(NodeSelectorRequirementFluent fluent, NodeSelectorRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeSelectorRequirementBuilder(NodeSelectorRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeSelectorRequirement build() {
      NodeSelectorRequirement buildable = new NodeSelectorRequirement(this.fluent.getKey(), this.fluent.getOperator(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
