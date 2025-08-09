package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodFailurePolicyOnExitCodesRequirementBuilder extends PodFailurePolicyOnExitCodesRequirementFluent implements VisitableBuilder {
   PodFailurePolicyOnExitCodesRequirementFluent fluent;

   public PodFailurePolicyOnExitCodesRequirementBuilder() {
      this(new PodFailurePolicyOnExitCodesRequirement());
   }

   public PodFailurePolicyOnExitCodesRequirementBuilder(PodFailurePolicyOnExitCodesRequirementFluent fluent) {
      this(fluent, new PodFailurePolicyOnExitCodesRequirement());
   }

   public PodFailurePolicyOnExitCodesRequirementBuilder(PodFailurePolicyOnExitCodesRequirementFluent fluent, PodFailurePolicyOnExitCodesRequirement instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodFailurePolicyOnExitCodesRequirementBuilder(PodFailurePolicyOnExitCodesRequirement instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodFailurePolicyOnExitCodesRequirement build() {
      PodFailurePolicyOnExitCodesRequirement buildable = new PodFailurePolicyOnExitCodesRequirement(this.fluent.getContainerName(), this.fluent.getOperator(), this.fluent.getValues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
