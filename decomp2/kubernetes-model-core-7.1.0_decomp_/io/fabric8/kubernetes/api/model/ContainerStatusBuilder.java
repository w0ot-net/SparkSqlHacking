package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerStatusBuilder extends ContainerStatusFluent implements VisitableBuilder {
   ContainerStatusFluent fluent;

   public ContainerStatusBuilder() {
      this(new ContainerStatus());
   }

   public ContainerStatusBuilder(ContainerStatusFluent fluent) {
      this(fluent, new ContainerStatus());
   }

   public ContainerStatusBuilder(ContainerStatusFluent fluent, ContainerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerStatusBuilder(ContainerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerStatus build() {
      ContainerStatus buildable = new ContainerStatus(this.fluent.getAllocatedResources(), this.fluent.buildAllocatedResourcesStatus(), this.fluent.getContainerID(), this.fluent.getImage(), this.fluent.getImageID(), this.fluent.buildLastState(), this.fluent.getName(), this.fluent.getReady(), this.fluent.buildResources(), this.fluent.getRestartCount(), this.fluent.getStarted(), this.fluent.buildState(), this.fluent.buildUser(), this.fluent.buildVolumeMounts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
