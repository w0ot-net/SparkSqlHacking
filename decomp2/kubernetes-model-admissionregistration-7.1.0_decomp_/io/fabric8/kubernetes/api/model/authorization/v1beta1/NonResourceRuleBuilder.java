package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NonResourceRuleBuilder extends NonResourceRuleFluent implements VisitableBuilder {
   NonResourceRuleFluent fluent;

   public NonResourceRuleBuilder() {
      this(new NonResourceRule());
   }

   public NonResourceRuleBuilder(NonResourceRuleFluent fluent) {
      this(fluent, new NonResourceRule());
   }

   public NonResourceRuleBuilder(NonResourceRuleFluent fluent, NonResourceRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NonResourceRuleBuilder(NonResourceRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NonResourceRule build() {
      NonResourceRule buildable = new NonResourceRule(this.fluent.getNonResourceURLs(), this.fluent.getVerbs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
