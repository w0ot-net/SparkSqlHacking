package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDisruptionBudgetSpecBuilder extends PodDisruptionBudgetSpecFluent implements VisitableBuilder {
   PodDisruptionBudgetSpecFluent fluent;

   public PodDisruptionBudgetSpecBuilder() {
      this(new PodDisruptionBudgetSpec());
   }

   public PodDisruptionBudgetSpecBuilder(PodDisruptionBudgetSpecFluent fluent) {
      this(fluent, new PodDisruptionBudgetSpec());
   }

   public PodDisruptionBudgetSpecBuilder(PodDisruptionBudgetSpecFluent fluent, PodDisruptionBudgetSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDisruptionBudgetSpecBuilder(PodDisruptionBudgetSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDisruptionBudgetSpec build() {
      PodDisruptionBudgetSpec buildable = new PodDisruptionBudgetSpec(this.fluent.buildMaxUnavailable(), this.fluent.buildMinAvailable(), this.fluent.buildSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
