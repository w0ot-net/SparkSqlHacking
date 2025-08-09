package io.fabric8.kubernetes.api.model.policy.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDisruptionBudgetListBuilder extends PodDisruptionBudgetListFluent implements VisitableBuilder {
   PodDisruptionBudgetListFluent fluent;

   public PodDisruptionBudgetListBuilder() {
      this(new PodDisruptionBudgetList());
   }

   public PodDisruptionBudgetListBuilder(PodDisruptionBudgetListFluent fluent) {
      this(fluent, new PodDisruptionBudgetList());
   }

   public PodDisruptionBudgetListBuilder(PodDisruptionBudgetListFluent fluent, PodDisruptionBudgetList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDisruptionBudgetListBuilder(PodDisruptionBudgetList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDisruptionBudgetList build() {
      PodDisruptionBudgetList buildable = new PodDisruptionBudgetList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
