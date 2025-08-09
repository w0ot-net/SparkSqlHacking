package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressLoadBalancerStatusBuilder extends IngressLoadBalancerStatusFluent implements VisitableBuilder {
   IngressLoadBalancerStatusFluent fluent;

   public IngressLoadBalancerStatusBuilder() {
      this(new IngressLoadBalancerStatus());
   }

   public IngressLoadBalancerStatusBuilder(IngressLoadBalancerStatusFluent fluent) {
      this(fluent, new IngressLoadBalancerStatus());
   }

   public IngressLoadBalancerStatusBuilder(IngressLoadBalancerStatusFluent fluent, IngressLoadBalancerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressLoadBalancerStatusBuilder(IngressLoadBalancerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressLoadBalancerStatus build() {
      IngressLoadBalancerStatus buildable = new IngressLoadBalancerStatus(this.fluent.buildIngress());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
