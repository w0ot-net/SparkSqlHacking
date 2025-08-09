package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PortStatusBuilder extends PortStatusFluent implements VisitableBuilder {
   PortStatusFluent fluent;

   public PortStatusBuilder() {
      this(new PortStatus());
   }

   public PortStatusBuilder(PortStatusFluent fluent) {
      this(fluent, new PortStatus());
   }

   public PortStatusBuilder(PortStatusFluent fluent, PortStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PortStatusBuilder(PortStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PortStatus build() {
      PortStatus buildable = new PortStatus(this.fluent.getError(), this.fluent.getPort(), this.fluent.getProtocol());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
