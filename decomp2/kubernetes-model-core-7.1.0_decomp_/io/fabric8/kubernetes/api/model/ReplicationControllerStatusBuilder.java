package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicationControllerStatusBuilder extends ReplicationControllerStatusFluent implements VisitableBuilder {
   ReplicationControllerStatusFluent fluent;

   public ReplicationControllerStatusBuilder() {
      this(new ReplicationControllerStatus());
   }

   public ReplicationControllerStatusBuilder(ReplicationControllerStatusFluent fluent) {
      this(fluent, new ReplicationControllerStatus());
   }

   public ReplicationControllerStatusBuilder(ReplicationControllerStatusFluent fluent, ReplicationControllerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicationControllerStatusBuilder(ReplicationControllerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicationControllerStatus build() {
      ReplicationControllerStatus buildable = new ReplicationControllerStatus(this.fluent.getAvailableReplicas(), this.fluent.buildConditions(), this.fluent.getFullyLabeledReplicas(), this.fluent.getObservedGeneration(), this.fluent.getReadyReplicas(), this.fluent.getReplicas());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
