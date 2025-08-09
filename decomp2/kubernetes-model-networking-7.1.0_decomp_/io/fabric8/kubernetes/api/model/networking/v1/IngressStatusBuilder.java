package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressStatusBuilder extends IngressStatusFluent implements VisitableBuilder {
   IngressStatusFluent fluent;

   public IngressStatusBuilder() {
      this(new IngressStatus());
   }

   public IngressStatusBuilder(IngressStatusFluent fluent) {
      this(fluent, new IngressStatus());
   }

   public IngressStatusBuilder(IngressStatusFluent fluent, IngressStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressStatusBuilder(IngressStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressStatus build() {
      IngressStatus buildable = new IngressStatus(this.fluent.buildLoadBalancer());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
