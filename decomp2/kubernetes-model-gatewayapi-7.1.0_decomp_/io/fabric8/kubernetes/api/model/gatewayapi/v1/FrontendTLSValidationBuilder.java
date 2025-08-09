package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FrontendTLSValidationBuilder extends FrontendTLSValidationFluent implements VisitableBuilder {
   FrontendTLSValidationFluent fluent;

   public FrontendTLSValidationBuilder() {
      this(new FrontendTLSValidation());
   }

   public FrontendTLSValidationBuilder(FrontendTLSValidationFluent fluent) {
      this(fluent, new FrontendTLSValidation());
   }

   public FrontendTLSValidationBuilder(FrontendTLSValidationFluent fluent, FrontendTLSValidation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FrontendTLSValidationBuilder(FrontendTLSValidation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FrontendTLSValidation build() {
      FrontendTLSValidation buildable = new FrontendTLSValidation(this.fluent.buildCaCertificateRefs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
