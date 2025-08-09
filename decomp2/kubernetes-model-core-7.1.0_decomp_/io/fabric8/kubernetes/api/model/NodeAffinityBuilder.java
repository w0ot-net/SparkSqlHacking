package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeAffinityBuilder extends NodeAffinityFluent implements VisitableBuilder {
   NodeAffinityFluent fluent;

   public NodeAffinityBuilder() {
      this(new NodeAffinity());
   }

   public NodeAffinityBuilder(NodeAffinityFluent fluent) {
      this(fluent, new NodeAffinity());
   }

   public NodeAffinityBuilder(NodeAffinityFluent fluent, NodeAffinity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeAffinityBuilder(NodeAffinity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeAffinity build() {
      NodeAffinity buildable = new NodeAffinity(this.fluent.buildPreferredDuringSchedulingIgnoredDuringExecution(), this.fluent.buildRequiredDuringSchedulingIgnoredDuringExecution());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
