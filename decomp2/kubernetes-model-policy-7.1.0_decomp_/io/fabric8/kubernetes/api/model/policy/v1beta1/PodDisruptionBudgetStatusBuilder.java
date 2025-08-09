package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDisruptionBudgetStatusBuilder extends PodDisruptionBudgetStatusFluent implements VisitableBuilder {
   PodDisruptionBudgetStatusFluent fluent;

   public PodDisruptionBudgetStatusBuilder() {
      this(new PodDisruptionBudgetStatus());
   }

   public PodDisruptionBudgetStatusBuilder(PodDisruptionBudgetStatusFluent fluent) {
      this(fluent, new PodDisruptionBudgetStatus());
   }

   public PodDisruptionBudgetStatusBuilder(PodDisruptionBudgetStatusFluent fluent, PodDisruptionBudgetStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDisruptionBudgetStatusBuilder(PodDisruptionBudgetStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDisruptionBudgetStatus build() {
      PodDisruptionBudgetStatus buildable = new PodDisruptionBudgetStatus(this.fluent.getConditions(), this.fluent.getCurrentHealthy(), this.fluent.getDesiredHealthy(), this.fluent.getDisruptedPods(), this.fluent.getDisruptionsAllowed(), this.fluent.getExpectedPods(), this.fluent.getObservedGeneration());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
