package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LoadBalancerStatusBuilder extends LoadBalancerStatusFluent implements VisitableBuilder {
   LoadBalancerStatusFluent fluent;

   public LoadBalancerStatusBuilder() {
      this(new LoadBalancerStatus());
   }

   public LoadBalancerStatusBuilder(LoadBalancerStatusFluent fluent) {
      this(fluent, new LoadBalancerStatus());
   }

   public LoadBalancerStatusBuilder(LoadBalancerStatusFluent fluent, LoadBalancerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LoadBalancerStatusBuilder(LoadBalancerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LoadBalancerStatus build() {
      LoadBalancerStatus buildable = new LoadBalancerStatus(this.fluent.buildIngress());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
