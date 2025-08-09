package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetStatusBuilder extends StatefulSetStatusFluent implements VisitableBuilder {
   StatefulSetStatusFluent fluent;

   public StatefulSetStatusBuilder() {
      this(new StatefulSetStatus());
   }

   public StatefulSetStatusBuilder(StatefulSetStatusFluent fluent) {
      this(fluent, new StatefulSetStatus());
   }

   public StatefulSetStatusBuilder(StatefulSetStatusFluent fluent, StatefulSetStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetStatusBuilder(StatefulSetStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetStatus build() {
      StatefulSetStatus buildable = new StatefulSetStatus(this.fluent.getAvailableReplicas(), this.fluent.getCollisionCount(), this.fluent.buildConditions(), this.fluent.getCurrentReplicas(), this.fluent.getCurrentRevision(), this.fluent.getObservedGeneration(), this.fluent.getReadyReplicas(), this.fluent.getReplicas(), this.fluent.getUpdateRevision(), this.fluent.getUpdatedReplicas());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
