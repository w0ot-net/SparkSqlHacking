package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServicePortBuilder extends ServicePortFluent implements VisitableBuilder {
   ServicePortFluent fluent;

   public ServicePortBuilder() {
      this(new ServicePort());
   }

   public ServicePortBuilder(ServicePortFluent fluent) {
      this(fluent, new ServicePort());
   }

   public ServicePortBuilder(ServicePortFluent fluent, ServicePort instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServicePortBuilder(ServicePort instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServicePort build() {
      ServicePort buildable = new ServicePort(this.fluent.getAppProtocol(), this.fluent.getName(), this.fluent.getNodePort(), this.fluent.getPort(), this.fluent.getProtocol(), this.fluent.buildTargetPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
