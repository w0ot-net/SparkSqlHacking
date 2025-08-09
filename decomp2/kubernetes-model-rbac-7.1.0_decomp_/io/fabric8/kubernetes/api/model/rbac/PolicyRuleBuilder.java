package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PolicyRuleBuilder extends PolicyRuleFluent implements VisitableBuilder {
   PolicyRuleFluent fluent;

   public PolicyRuleBuilder() {
      this(new PolicyRule());
   }

   public PolicyRuleBuilder(PolicyRuleFluent fluent) {
      this(fluent, new PolicyRule());
   }

   public PolicyRuleBuilder(PolicyRuleFluent fluent, PolicyRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PolicyRuleBuilder(PolicyRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PolicyRule build() {
      PolicyRule buildable = new PolicyRule(this.fluent.getApiGroups(), this.fluent.getNonResourceURLs(), this.fluent.getResourceNames(), this.fluent.getResources(), this.fluent.getVerbs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
