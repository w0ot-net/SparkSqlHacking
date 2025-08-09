package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerResizePolicyBuilder extends ContainerResizePolicyFluent implements VisitableBuilder {
   ContainerResizePolicyFluent fluent;

   public ContainerResizePolicyBuilder() {
      this(new ContainerResizePolicy());
   }

   public ContainerResizePolicyBuilder(ContainerResizePolicyFluent fluent) {
      this(fluent, new ContainerResizePolicy());
   }

   public ContainerResizePolicyBuilder(ContainerResizePolicyFluent fluent, ContainerResizePolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerResizePolicyBuilder(ContainerResizePolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerResizePolicy build() {
      ContainerResizePolicy buildable = new ContainerResizePolicy(this.fluent.getResourceName(), this.fluent.getRestartPolicy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
