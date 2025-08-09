package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NonResourcePolicyRuleBuilder extends NonResourcePolicyRuleFluent implements VisitableBuilder {
   NonResourcePolicyRuleFluent fluent;

   public NonResourcePolicyRuleBuilder() {
      this(new NonResourcePolicyRule());
   }

   public NonResourcePolicyRuleBuilder(NonResourcePolicyRuleFluent fluent) {
      this(fluent, new NonResourcePolicyRule());
   }

   public NonResourcePolicyRuleBuilder(NonResourcePolicyRuleFluent fluent, NonResourcePolicyRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NonResourcePolicyRuleBuilder(NonResourcePolicyRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NonResourcePolicyRule build() {
      NonResourcePolicyRule buildable = new NonResourcePolicyRule(this.fluent.getNonResourceURLs(), this.fluent.getVerbs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
