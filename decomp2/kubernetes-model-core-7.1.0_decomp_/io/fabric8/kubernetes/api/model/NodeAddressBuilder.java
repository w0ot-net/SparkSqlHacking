package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeAddressBuilder extends NodeAddressFluent implements VisitableBuilder {
   NodeAddressFluent fluent;

   public NodeAddressBuilder() {
      this(new NodeAddress());
   }

   public NodeAddressBuilder(NodeAddressFluent fluent) {
      this(fluent, new NodeAddress());
   }

   public NodeAddressBuilder(NodeAddressFluent fluent, NodeAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeAddressBuilder(NodeAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeAddress build() {
      NodeAddress buildable = new NodeAddress(this.fluent.getAddress(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
