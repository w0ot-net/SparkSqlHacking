package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceValidationBuilder extends CustomResourceValidationFluent implements VisitableBuilder {
   CustomResourceValidationFluent fluent;

   public CustomResourceValidationBuilder() {
      this(new CustomResourceValidation());
   }

   public CustomResourceValidationBuilder(CustomResourceValidationFluent fluent) {
      this(fluent, new CustomResourceValidation());
   }

   public CustomResourceValidationBuilder(CustomResourceValidationFluent fluent, CustomResourceValidation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceValidationBuilder(CustomResourceValidation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceValidation build() {
      CustomResourceValidation buildable = new CustomResourceValidation(this.fluent.buildOpenAPIV3Schema());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
