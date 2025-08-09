package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendTLSPolicyValidationBuilder extends BackendTLSPolicyValidationFluent implements VisitableBuilder {
   BackendTLSPolicyValidationFluent fluent;

   public BackendTLSPolicyValidationBuilder() {
      this(new BackendTLSPolicyValidation());
   }

   public BackendTLSPolicyValidationBuilder(BackendTLSPolicyValidationFluent fluent) {
      this(fluent, new BackendTLSPolicyValidation());
   }

   public BackendTLSPolicyValidationBuilder(BackendTLSPolicyValidationFluent fluent, BackendTLSPolicyValidation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendTLSPolicyValidationBuilder(BackendTLSPolicyValidation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendTLSPolicyValidation build() {
      BackendTLSPolicyValidation buildable = new BackendTLSPolicyValidation(this.fluent.buildCaCertificateRefs(), this.fluent.getHostname(), this.fluent.buildSubjectAltNames(), this.fluent.getWellKnownCACertificates());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
