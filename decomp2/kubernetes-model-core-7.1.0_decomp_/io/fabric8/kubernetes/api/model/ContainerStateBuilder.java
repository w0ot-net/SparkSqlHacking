package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerStateBuilder extends ContainerStateFluent implements VisitableBuilder {
   ContainerStateFluent fluent;

   public ContainerStateBuilder() {
      this(new ContainerState());
   }

   public ContainerStateBuilder(ContainerStateFluent fluent) {
      this(fluent, new ContainerState());
   }

   public ContainerStateBuilder(ContainerStateFluent fluent, ContainerState instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerStateBuilder(ContainerState instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerState build() {
      ContainerState buildable = new ContainerState(this.fluent.buildRunning(), this.fluent.buildTerminated(), this.fluent.buildWaiting());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
