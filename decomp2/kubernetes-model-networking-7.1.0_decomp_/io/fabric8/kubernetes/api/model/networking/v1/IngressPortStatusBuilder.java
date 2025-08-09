package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressPortStatusBuilder extends IngressPortStatusFluent implements VisitableBuilder {
   IngressPortStatusFluent fluent;

   public IngressPortStatusBuilder() {
      this(new IngressPortStatus());
   }

   public IngressPortStatusBuilder(IngressPortStatusFluent fluent) {
      this(fluent, new IngressPortStatus());
   }

   public IngressPortStatusBuilder(IngressPortStatusFluent fluent, IngressPortStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressPortStatusBuilder(IngressPortStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressPortStatus build() {
      IngressPortStatus buildable = new IngressPortStatus(this.fluent.getError(), this.fluent.getPort(), this.fluent.getProtocol());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
