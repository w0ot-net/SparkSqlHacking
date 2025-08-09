package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceSpecBuilder extends ServiceSpecFluent implements VisitableBuilder {
   ServiceSpecFluent fluent;

   public ServiceSpecBuilder() {
      this(new ServiceSpec());
   }

   public ServiceSpecBuilder(ServiceSpecFluent fluent) {
      this(fluent, new ServiceSpec());
   }

   public ServiceSpecBuilder(ServiceSpecFluent fluent, ServiceSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceSpecBuilder(ServiceSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceSpec build() {
      ServiceSpec buildable = new ServiceSpec(this.fluent.getAllocateLoadBalancerNodePorts(), this.fluent.getClusterIP(), this.fluent.getClusterIPs(), this.fluent.getExternalIPs(), this.fluent.getExternalName(), this.fluent.getExternalTrafficPolicy(), this.fluent.getHealthCheckNodePort(), this.fluent.getInternalTrafficPolicy(), this.fluent.getIpFamilies(), this.fluent.getIpFamilyPolicy(), this.fluent.getLoadBalancerClass(), this.fluent.getLoadBalancerIP(), this.fluent.getLoadBalancerSourceRanges(), this.fluent.buildPorts(), this.fluent.getPublishNotReadyAddresses(), this.fluent.getSelector(), this.fluent.getSessionAffinity(), this.fluent.buildSessionAffinityConfig(), this.fluent.getTrafficDistribution(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
