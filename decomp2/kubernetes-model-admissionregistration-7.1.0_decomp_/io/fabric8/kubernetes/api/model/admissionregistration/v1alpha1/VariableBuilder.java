package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VariableBuilder extends VariableFluent implements VisitableBuilder {
   VariableFluent fluent;

   public VariableBuilder() {
      this(new Variable());
   }

   public VariableBuilder(VariableFluent fluent) {
      this(fluent, new Variable());
   }

   public VariableBuilder(VariableFluent fluent, Variable instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VariableBuilder(Variable instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Variable build() {
      Variable buildable = new Variable(this.fluent.getExpression(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
