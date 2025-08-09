package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerStateRunningBuilder extends ContainerStateRunningFluent implements VisitableBuilder {
   ContainerStateRunningFluent fluent;

   public ContainerStateRunningBuilder() {
      this(new ContainerStateRunning());
   }

   public ContainerStateRunningBuilder(ContainerStateRunningFluent fluent) {
      this(fluent, new ContainerStateRunning());
   }

   public ContainerStateRunningBuilder(ContainerStateRunningFluent fluent, ContainerStateRunning instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerStateRunningBuilder(ContainerStateRunning instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerStateRunning build() {
      ContainerStateRunning buildable = new ContainerStateRunning(this.fluent.getStartedAt());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
