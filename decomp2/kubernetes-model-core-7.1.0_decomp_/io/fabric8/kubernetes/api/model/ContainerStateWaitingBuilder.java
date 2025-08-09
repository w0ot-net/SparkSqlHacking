package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerStateWaitingBuilder extends ContainerStateWaitingFluent implements VisitableBuilder {
   ContainerStateWaitingFluent fluent;

   public ContainerStateWaitingBuilder() {
      this(new ContainerStateWaiting());
   }

   public ContainerStateWaitingBuilder(ContainerStateWaitingFluent fluent) {
      this(fluent, new ContainerStateWaiting());
   }

   public ContainerStateWaitingBuilder(ContainerStateWaitingFluent fluent, ContainerStateWaiting instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerStateWaitingBuilder(ContainerStateWaiting instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerStateWaiting build() {
      ContainerStateWaiting buildable = new ContainerStateWaiting(this.fluent.getMessage(), this.fluent.getReason());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
