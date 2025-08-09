package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicaSetStatusBuilder extends ReplicaSetStatusFluent implements VisitableBuilder {
   ReplicaSetStatusFluent fluent;

   public ReplicaSetStatusBuilder() {
      this(new ReplicaSetStatus());
   }

   public ReplicaSetStatusBuilder(ReplicaSetStatusFluent fluent) {
      this(fluent, new ReplicaSetStatus());
   }

   public ReplicaSetStatusBuilder(ReplicaSetStatusFluent fluent, ReplicaSetStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicaSetStatusBuilder(ReplicaSetStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicaSetStatus build() {
      ReplicaSetStatus buildable = new ReplicaSetStatus(this.fluent.getAvailableReplicas(), this.fluent.buildConditions(), this.fluent.getFullyLabeledReplicas(), this.fluent.getObservedGeneration(), this.fluent.getReadyReplicas(), this.fluent.getReplicas());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
