package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDisruptionBudgetBuilder extends PodDisruptionBudgetFluent implements VisitableBuilder {
   PodDisruptionBudgetFluent fluent;

   public PodDisruptionBudgetBuilder() {
      this(new PodDisruptionBudget());
   }

   public PodDisruptionBudgetBuilder(PodDisruptionBudgetFluent fluent) {
      this(fluent, new PodDisruptionBudget());
   }

   public PodDisruptionBudgetBuilder(PodDisruptionBudgetFluent fluent, PodDisruptionBudget instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDisruptionBudgetBuilder(PodDisruptionBudget instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDisruptionBudget build() {
      PodDisruptionBudget buildable = new PodDisruptionBudget(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
