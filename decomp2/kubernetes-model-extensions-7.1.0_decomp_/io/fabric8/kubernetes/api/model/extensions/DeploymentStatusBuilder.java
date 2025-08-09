package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeploymentStatusBuilder extends DeploymentStatusFluent implements VisitableBuilder {
   DeploymentStatusFluent fluent;

   public DeploymentStatusBuilder() {
      this(new DeploymentStatus());
   }

   public DeploymentStatusBuilder(DeploymentStatusFluent fluent) {
      this(fluent, new DeploymentStatus());
   }

   public DeploymentStatusBuilder(DeploymentStatusFluent fluent, DeploymentStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeploymentStatusBuilder(DeploymentStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeploymentStatus build() {
      DeploymentStatus buildable = new DeploymentStatus(this.fluent.getAvailableReplicas(), this.fluent.getCollisionCount(), this.fluent.buildConditions(), this.fluent.getObservedGeneration(), this.fluent.getReadyReplicas(), this.fluent.getReplicas(), this.fluent.getUnavailableReplicas(), this.fluent.getUpdatedReplicas());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
