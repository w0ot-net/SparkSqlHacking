package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeStatusBuilder extends NodeStatusFluent implements VisitableBuilder {
   NodeStatusFluent fluent;

   public NodeStatusBuilder() {
      this(new NodeStatus());
   }

   public NodeStatusBuilder(NodeStatusFluent fluent) {
      this(fluent, new NodeStatus());
   }

   public NodeStatusBuilder(NodeStatusFluent fluent, NodeStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeStatusBuilder(NodeStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeStatus build() {
      NodeStatus buildable = new NodeStatus(this.fluent.buildAddresses(), this.fluent.getAllocatable(), this.fluent.getCapacity(), this.fluent.buildConditions(), this.fluent.buildConfig(), this.fluent.buildDaemonEndpoints(), this.fluent.buildFeatures(), this.fluent.buildImages(), this.fluent.buildNodeInfo(), this.fluent.getPhase(), this.fluent.buildRuntimeHandlers(), this.fluent.buildVolumesAttached(), this.fluent.getVolumesInUse());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
