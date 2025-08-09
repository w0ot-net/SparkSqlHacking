package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SuccessPolicyRuleBuilder extends SuccessPolicyRuleFluent implements VisitableBuilder {
   SuccessPolicyRuleFluent fluent;

   public SuccessPolicyRuleBuilder() {
      this(new SuccessPolicyRule());
   }

   public SuccessPolicyRuleBuilder(SuccessPolicyRuleFluent fluent) {
      this(fluent, new SuccessPolicyRule());
   }

   public SuccessPolicyRuleBuilder(SuccessPolicyRuleFluent fluent, SuccessPolicyRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SuccessPolicyRuleBuilder(SuccessPolicyRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SuccessPolicyRule build() {
      SuccessPolicyRule buildable = new SuccessPolicyRule(this.fluent.getSucceededCount(), this.fluent.getSucceededIndexes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
