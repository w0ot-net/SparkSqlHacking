package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicaSetConditionBuilder extends ReplicaSetConditionFluent implements VisitableBuilder {
   ReplicaSetConditionFluent fluent;

   public ReplicaSetConditionBuilder() {
      this(new ReplicaSetCondition());
   }

   public ReplicaSetConditionBuilder(ReplicaSetConditionFluent fluent) {
      this(fluent, new ReplicaSetCondition());
   }

   public ReplicaSetConditionBuilder(ReplicaSetConditionFluent fluent, ReplicaSetCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicaSetConditionBuilder(ReplicaSetCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicaSetCondition build() {
      ReplicaSetCondition buildable = new ReplicaSetCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
