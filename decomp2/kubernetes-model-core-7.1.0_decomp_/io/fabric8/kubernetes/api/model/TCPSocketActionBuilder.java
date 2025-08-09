package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPSocketActionBuilder extends TCPSocketActionFluent implements VisitableBuilder {
   TCPSocketActionFluent fluent;

   public TCPSocketActionBuilder() {
      this(new TCPSocketAction());
   }

   public TCPSocketActionBuilder(TCPSocketActionFluent fluent) {
      this(fluent, new TCPSocketAction());
   }

   public TCPSocketActionBuilder(TCPSocketActionFluent fluent, TCPSocketAction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPSocketActionBuilder(TCPSocketAction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPSocketAction build() {
      TCPSocketAction buildable = new TCPSocketAction(this.fluent.getHost(), this.fluent.buildPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
