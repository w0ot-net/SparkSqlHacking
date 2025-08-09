package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LoadBalancerIngressBuilder extends LoadBalancerIngressFluent implements VisitableBuilder {
   LoadBalancerIngressFluent fluent;

   public LoadBalancerIngressBuilder() {
      this(new LoadBalancerIngress());
   }

   public LoadBalancerIngressBuilder(LoadBalancerIngressFluent fluent) {
      this(fluent, new LoadBalancerIngress());
   }

   public LoadBalancerIngressBuilder(LoadBalancerIngressFluent fluent, LoadBalancerIngress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LoadBalancerIngressBuilder(LoadBalancerIngress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LoadBalancerIngress build() {
      LoadBalancerIngress buildable = new LoadBalancerIngress(this.fluent.getHostname(), this.fluent.getIp(), this.fluent.getIpMode(), this.fluent.buildPorts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
