package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerStateTerminatedBuilder extends ContainerStateTerminatedFluent implements VisitableBuilder {
   ContainerStateTerminatedFluent fluent;

   public ContainerStateTerminatedBuilder() {
      this(new ContainerStateTerminated());
   }

   public ContainerStateTerminatedBuilder(ContainerStateTerminatedFluent fluent) {
      this(fluent, new ContainerStateTerminated());
   }

   public ContainerStateTerminatedBuilder(ContainerStateTerminatedFluent fluent, ContainerStateTerminated instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerStateTerminatedBuilder(ContainerStateTerminated instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerStateTerminated build() {
      ContainerStateTerminated buildable = new ContainerStateTerminated(this.fluent.getContainerID(), this.fluent.getExitCode(), this.fluent.getFinishedAt(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getSignal(), this.fluent.getStartedAt());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
