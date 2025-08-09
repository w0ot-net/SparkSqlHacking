package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeBuilder extends NodeFluent implements VisitableBuilder {
   NodeFluent fluent;

   public NodeBuilder() {
      this(new Node());
   }

   public NodeBuilder(NodeFluent fluent) {
      this(fluent, new Node());
   }

   public NodeBuilder(NodeFluent fluent, Node instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeBuilder(Node instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Node build() {
      Node buildable = new Node(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
