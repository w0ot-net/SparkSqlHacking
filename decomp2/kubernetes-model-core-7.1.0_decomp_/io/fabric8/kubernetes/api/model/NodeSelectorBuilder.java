package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeSelectorBuilder extends NodeSelectorFluent implements VisitableBuilder {
   NodeSelectorFluent fluent;

   public NodeSelectorBuilder() {
      this(new NodeSelector());
   }

   public NodeSelectorBuilder(NodeSelectorFluent fluent) {
      this(fluent, new NodeSelector());
   }

   public NodeSelectorBuilder(NodeSelectorFluent fluent, NodeSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeSelectorBuilder(NodeSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeSelector build() {
      NodeSelector buildable = new NodeSelector(this.fluent.buildNodeSelectorTerms());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
