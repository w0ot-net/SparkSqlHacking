package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodFailurePolicyOnPodConditionsPatternBuilder extends PodFailurePolicyOnPodConditionsPatternFluent implements VisitableBuilder {
   PodFailurePolicyOnPodConditionsPatternFluent fluent;

   public PodFailurePolicyOnPodConditionsPatternBuilder() {
      this(new PodFailurePolicyOnPodConditionsPattern());
   }

   public PodFailurePolicyOnPodConditionsPatternBuilder(PodFailurePolicyOnPodConditionsPatternFluent fluent) {
      this(fluent, new PodFailurePolicyOnPodConditionsPattern());
   }

   public PodFailurePolicyOnPodConditionsPatternBuilder(PodFailurePolicyOnPodConditionsPatternFluent fluent, PodFailurePolicyOnPodConditionsPattern instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodFailurePolicyOnPodConditionsPatternBuilder(PodFailurePolicyOnPodConditionsPattern instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodFailurePolicyOnPodConditionsPattern build() {
      PodFailurePolicyOnPodConditionsPattern buildable = new PodFailurePolicyOnPodConditionsPattern(this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
