package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressLoadBalancerIngressBuilder extends IngressLoadBalancerIngressFluent implements VisitableBuilder {
   IngressLoadBalancerIngressFluent fluent;

   public IngressLoadBalancerIngressBuilder() {
      this(new IngressLoadBalancerIngress());
   }

   public IngressLoadBalancerIngressBuilder(IngressLoadBalancerIngressFluent fluent) {
      this(fluent, new IngressLoadBalancerIngress());
   }

   public IngressLoadBalancerIngressBuilder(IngressLoadBalancerIngressFluent fluent, IngressLoadBalancerIngress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressLoadBalancerIngressBuilder(IngressLoadBalancerIngress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressLoadBalancerIngress build() {
      IngressLoadBalancerIngress buildable = new IngressLoadBalancerIngress(this.fluent.getHostname(), this.fluent.getIp(), this.fluent.buildPorts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
