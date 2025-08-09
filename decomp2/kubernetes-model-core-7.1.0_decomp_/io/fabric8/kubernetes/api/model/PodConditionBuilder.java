package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodConditionBuilder extends PodConditionFluent implements VisitableBuilder {
   PodConditionFluent fluent;

   public PodConditionBuilder() {
      this(new PodCondition());
   }

   public PodConditionBuilder(PodConditionFluent fluent) {
      this(fluent, new PodCondition());
   }

   public PodConditionBuilder(PodConditionFluent fluent, PodCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodConditionBuilder(PodCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodCondition build() {
      PodCondition buildable = new PodCondition(this.fluent.getLastProbeTime(), this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
