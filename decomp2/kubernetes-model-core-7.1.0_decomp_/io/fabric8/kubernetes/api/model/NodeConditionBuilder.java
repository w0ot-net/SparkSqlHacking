package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeConditionBuilder extends NodeConditionFluent implements VisitableBuilder {
   NodeConditionFluent fluent;

   public NodeConditionBuilder() {
      this(new NodeCondition());
   }

   public NodeConditionBuilder(NodeConditionFluent fluent) {
      this(fluent, new NodeCondition());
   }

   public NodeConditionBuilder(NodeConditionFluent fluent, NodeCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeConditionBuilder(NodeCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeCondition build() {
      NodeCondition buildable = new NodeCondition(this.fluent.getLastHeartbeatTime(), this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
