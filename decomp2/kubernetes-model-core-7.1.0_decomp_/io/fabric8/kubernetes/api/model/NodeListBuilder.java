package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeListBuilder extends NodeListFluent implements VisitableBuilder {
   NodeListFluent fluent;

   public NodeListBuilder() {
      this(new NodeList());
   }

   public NodeListBuilder(NodeListFluent fluent) {
      this(fluent, new NodeList());
   }

   public NodeListBuilder(NodeListFluent fluent, NodeList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeListBuilder(NodeList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeList build() {
      NodeList buildable = new NodeList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
