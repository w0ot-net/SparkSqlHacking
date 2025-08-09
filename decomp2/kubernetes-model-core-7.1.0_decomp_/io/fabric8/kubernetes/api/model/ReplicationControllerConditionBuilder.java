package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicationControllerConditionBuilder extends ReplicationControllerConditionFluent implements VisitableBuilder {
   ReplicationControllerConditionFluent fluent;

   public ReplicationControllerConditionBuilder() {
      this(new ReplicationControllerCondition());
   }

   public ReplicationControllerConditionBuilder(ReplicationControllerConditionFluent fluent) {
      this(fluent, new ReplicationControllerCondition());
   }

   public ReplicationControllerConditionBuilder(ReplicationControllerConditionFluent fluent, ReplicationControllerCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicationControllerConditionBuilder(ReplicationControllerCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicationControllerCondition build() {
      ReplicationControllerCondition buildable = new ReplicationControllerCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
