package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidationBuilder extends ValidationFluent implements VisitableBuilder {
   ValidationFluent fluent;

   public ValidationBuilder() {
      this(new Validation());
   }

   public ValidationBuilder(ValidationFluent fluent) {
      this(fluent, new Validation());
   }

   public ValidationBuilder(ValidationFluent fluent, Validation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidationBuilder(Validation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Validation build() {
      Validation buildable = new Validation(this.fluent.getExpression(), this.fluent.getMessage(), this.fluent.getMessageExpression(), this.fluent.getReason());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
