package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConditionBuilder extends ConditionFluent implements VisitableBuilder {
   ConditionFluent fluent;

   public ConditionBuilder() {
      this(new Condition());
   }

   public ConditionBuilder(ConditionFluent fluent) {
      this(fluent, new Condition());
   }

   public ConditionBuilder(ConditionFluent fluent, Condition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConditionBuilder(Condition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Condition build() {
      Condition buildable = new Condition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getObservedGeneration(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
