package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeConfigStatusBuilder extends NodeConfigStatusFluent implements VisitableBuilder {
   NodeConfigStatusFluent fluent;

   public NodeConfigStatusBuilder() {
      this(new NodeConfigStatus());
   }

   public NodeConfigStatusBuilder(NodeConfigStatusFluent fluent) {
      this(fluent, new NodeConfigStatus());
   }

   public NodeConfigStatusBuilder(NodeConfigStatusFluent fluent, NodeConfigStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeConfigStatusBuilder(NodeConfigStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeConfigStatus build() {
      NodeConfigStatus buildable = new NodeConfigStatus(this.fluent.buildActive(), this.fluent.buildAssigned(), this.fluent.getError(), this.fluent.buildLastKnownGood());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
