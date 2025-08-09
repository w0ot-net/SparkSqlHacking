package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExpressionWarningBuilder extends ExpressionWarningFluent implements VisitableBuilder {
   ExpressionWarningFluent fluent;

   public ExpressionWarningBuilder() {
      this(new ExpressionWarning());
   }

   public ExpressionWarningBuilder(ExpressionWarningFluent fluent) {
      this(fluent, new ExpressionWarning());
   }

   public ExpressionWarningBuilder(ExpressionWarningFluent fluent, ExpressionWarning instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExpressionWarningBuilder(ExpressionWarning instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExpressionWarning build() {
      ExpressionWarning buildable = new ExpressionWarning(this.fluent.getFieldRef(), this.fluent.getWarning());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
