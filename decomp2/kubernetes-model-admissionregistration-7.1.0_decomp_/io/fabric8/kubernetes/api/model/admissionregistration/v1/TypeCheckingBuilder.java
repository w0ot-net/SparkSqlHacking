package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TypeCheckingBuilder extends TypeCheckingFluent implements VisitableBuilder {
   TypeCheckingFluent fluent;

   public TypeCheckingBuilder() {
      this(new TypeChecking());
   }

   public TypeCheckingBuilder(TypeCheckingFluent fluent) {
      this(fluent, new TypeChecking());
   }

   public TypeCheckingBuilder(TypeCheckingFluent fluent, TypeChecking instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TypeCheckingBuilder(TypeChecking instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TypeChecking build() {
      TypeChecking buildable = new TypeChecking(this.fluent.buildExpressionWarnings());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
