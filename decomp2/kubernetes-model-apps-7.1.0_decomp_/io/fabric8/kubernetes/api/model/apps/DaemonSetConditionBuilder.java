package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetConditionBuilder extends DaemonSetConditionFluent implements VisitableBuilder {
   DaemonSetConditionFluent fluent;

   public DaemonSetConditionBuilder() {
      this(new DaemonSetCondition());
   }

   public DaemonSetConditionBuilder(DaemonSetConditionFluent fluent) {
      this(fluent, new DaemonSetCondition());
   }

   public DaemonSetConditionBuilder(DaemonSetConditionFluent fluent, DaemonSetCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetConditionBuilder(DaemonSetCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSetCondition build() {
      DaemonSetCondition buildable = new DaemonSetCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
