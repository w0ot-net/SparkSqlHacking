package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HPAScalingRulesBuilder extends HPAScalingRulesFluent implements VisitableBuilder {
   HPAScalingRulesFluent fluent;

   public HPAScalingRulesBuilder() {
      this(new HPAScalingRules());
   }

   public HPAScalingRulesBuilder(HPAScalingRulesFluent fluent) {
      this(fluent, new HPAScalingRules());
   }

   public HPAScalingRulesBuilder(HPAScalingRulesFluent fluent, HPAScalingRules instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HPAScalingRulesBuilder(HPAScalingRules instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HPAScalingRules build() {
      HPAScalingRules buildable = new HPAScalingRules(this.fluent.buildPolicies(), this.fluent.getSelectPolicy(), this.fluent.getStabilizationWindowSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
