package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AuditAnnotationBuilder extends AuditAnnotationFluent implements VisitableBuilder {
   AuditAnnotationFluent fluent;

   public AuditAnnotationBuilder() {
      this(new AuditAnnotation());
   }

   public AuditAnnotationBuilder(AuditAnnotationFluent fluent) {
      this(fluent, new AuditAnnotation());
   }

   public AuditAnnotationBuilder(AuditAnnotationFluent fluent, AuditAnnotation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AuditAnnotationBuilder(AuditAnnotation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AuditAnnotation build() {
      AuditAnnotation buildable = new AuditAnnotation(this.fluent.getKey(), this.fluent.getValueExpression());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
