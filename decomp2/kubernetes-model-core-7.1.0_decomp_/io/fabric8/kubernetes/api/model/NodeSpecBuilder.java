package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeSpecBuilder extends NodeSpecFluent implements VisitableBuilder {
   NodeSpecFluent fluent;

   public NodeSpecBuilder() {
      this(new NodeSpec());
   }

   public NodeSpecBuilder(NodeSpecFluent fluent) {
      this(fluent, new NodeSpec());
   }

   public NodeSpecBuilder(NodeSpecFluent fluent, NodeSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeSpecBuilder(NodeSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeSpec build() {
      NodeSpec buildable = new NodeSpec(this.fluent.buildConfigSource(), this.fluent.getExternalID(), this.fluent.getPodCIDR(), this.fluent.getPodCIDRs(), this.fluent.getProviderID(), this.fluent.buildTaints(), this.fluent.getUnschedulable());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
