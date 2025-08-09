package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetStatusBuilder extends DaemonSetStatusFluent implements VisitableBuilder {
   DaemonSetStatusFluent fluent;

   public DaemonSetStatusBuilder() {
      this(new DaemonSetStatus());
   }

   public DaemonSetStatusBuilder(DaemonSetStatusFluent fluent) {
      this(fluent, new DaemonSetStatus());
   }

   public DaemonSetStatusBuilder(DaemonSetStatusFluent fluent, DaemonSetStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetStatusBuilder(DaemonSetStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSetStatus build() {
      DaemonSetStatus buildable = new DaemonSetStatus(this.fluent.getCollisionCount(), this.fluent.buildConditions(), this.fluent.getCurrentNumberScheduled(), this.fluent.getDesiredNumberScheduled(), this.fluent.getNumberAvailable(), this.fluent.getNumberMisscheduled(), this.fluent.getNumberReady(), this.fluent.getNumberUnavailable(), this.fluent.getObservedGeneration(), this.fluent.getUpdatedNumberScheduled());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
