package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeSelectorTermBuilder extends NodeSelectorTermFluent implements VisitableBuilder {
   NodeSelectorTermFluent fluent;

   public NodeSelectorTermBuilder() {
      this(new NodeSelectorTerm());
   }

   public NodeSelectorTermBuilder(NodeSelectorTermFluent fluent) {
      this(fluent, new NodeSelectorTerm());
   }

   public NodeSelectorTermBuilder(NodeSelectorTermFluent fluent, NodeSelectorTerm instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeSelectorTermBuilder(NodeSelectorTerm instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeSelectorTerm build() {
      NodeSelectorTerm buildable = new NodeSelectorTerm(this.fluent.buildMatchExpressions(), this.fluent.buildMatchFields());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
