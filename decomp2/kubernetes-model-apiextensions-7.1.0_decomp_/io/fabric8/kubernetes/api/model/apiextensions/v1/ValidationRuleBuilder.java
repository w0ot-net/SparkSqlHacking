package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidationRuleBuilder extends ValidationRuleFluent implements VisitableBuilder {
   ValidationRuleFluent fluent;

   public ValidationRuleBuilder() {
      this(new ValidationRule());
   }

   public ValidationRuleBuilder(ValidationRuleFluent fluent) {
      this(fluent, new ValidationRule());
   }

   public ValidationRuleBuilder(ValidationRuleFluent fluent, ValidationRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidationRuleBuilder(ValidationRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidationRule build() {
      ValidationRule buildable = new ValidationRule(this.fluent.getFieldPath(), this.fluent.getMessage(), this.fluent.getMessageExpression(), this.fluent.getOptionalOldSelf(), this.fluent.getReason(), this.fluent.getRule());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
