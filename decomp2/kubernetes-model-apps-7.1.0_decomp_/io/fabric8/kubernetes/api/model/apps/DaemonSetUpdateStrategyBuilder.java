package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetUpdateStrategyBuilder extends DaemonSetUpdateStrategyFluent implements VisitableBuilder {
   DaemonSetUpdateStrategyFluent fluent;

   public DaemonSetUpdateStrategyBuilder() {
      this(new DaemonSetUpdateStrategy());
   }

   public DaemonSetUpdateStrategyBuilder(DaemonSetUpdateStrategyFluent fluent) {
      this(fluent, new DaemonSetUpdateStrategy());
   }

   public DaemonSetUpdateStrategyBuilder(DaemonSetUpdateStrategyFluent fluent, DaemonSetUpdateStrategy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetUpdateStrategyBuilder(DaemonSetUpdateStrategy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSetUpdateStrategy build() {
      DaemonSetUpdateStrategy buildable = new DaemonSetUpdateStrategy(this.fluent.buildRollingUpdate(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
