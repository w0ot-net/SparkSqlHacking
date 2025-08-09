package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodFailurePolicyRuleBuilder extends PodFailurePolicyRuleFluent implements VisitableBuilder {
   PodFailurePolicyRuleFluent fluent;

   public PodFailurePolicyRuleBuilder() {
      this(new PodFailurePolicyRule());
   }

   public PodFailurePolicyRuleBuilder(PodFailurePolicyRuleFluent fluent) {
      this(fluent, new PodFailurePolicyRule());
   }

   public PodFailurePolicyRuleBuilder(PodFailurePolicyRuleFluent fluent, PodFailurePolicyRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodFailurePolicyRuleBuilder(PodFailurePolicyRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodFailurePolicyRule build() {
      PodFailurePolicyRule buildable = new PodFailurePolicyRule(this.fluent.getAction(), this.fluent.buildOnExitCodes(), this.fluent.buildOnPodConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
