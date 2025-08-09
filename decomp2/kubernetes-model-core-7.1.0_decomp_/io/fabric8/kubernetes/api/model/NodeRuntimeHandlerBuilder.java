package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeRuntimeHandlerBuilder extends NodeRuntimeHandlerFluent implements VisitableBuilder {
   NodeRuntimeHandlerFluent fluent;

   public NodeRuntimeHandlerBuilder() {
      this(new NodeRuntimeHandler());
   }

   public NodeRuntimeHandlerBuilder(NodeRuntimeHandlerFluent fluent) {
      this(fluent, new NodeRuntimeHandler());
   }

   public NodeRuntimeHandlerBuilder(NodeRuntimeHandlerFluent fluent, NodeRuntimeHandler instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeRuntimeHandlerBuilder(NodeRuntimeHandler instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeRuntimeHandler build() {
      NodeRuntimeHandler buildable = new NodeRuntimeHandler(this.fluent.buildFeatures(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
