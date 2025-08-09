package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerPortBuilder extends ContainerPortFluent implements VisitableBuilder {
   ContainerPortFluent fluent;

   public ContainerPortBuilder() {
      this(new ContainerPort());
   }

   public ContainerPortBuilder(ContainerPortFluent fluent) {
      this(fluent, new ContainerPort());
   }

   public ContainerPortBuilder(ContainerPortFluent fluent, ContainerPort instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerPortBuilder(ContainerPort instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerPort build() {
      ContainerPort buildable = new ContainerPort(this.fluent.getContainerPort(), this.fluent.getHostIP(), this.fluent.getHostPort(), this.fluent.getName(), this.fluent.getProtocol());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
