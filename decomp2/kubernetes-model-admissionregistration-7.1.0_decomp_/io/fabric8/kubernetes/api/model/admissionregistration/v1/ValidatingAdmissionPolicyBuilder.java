package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyBuilder extends ValidatingAdmissionPolicyFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyFluent fluent;

   public ValidatingAdmissionPolicyBuilder() {
      this(new ValidatingAdmissionPolicy());
   }

   public ValidatingAdmissionPolicyBuilder(ValidatingAdmissionPolicyFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicy());
   }

   public ValidatingAdmissionPolicyBuilder(ValidatingAdmissionPolicyFluent fluent, ValidatingAdmissionPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBuilder(ValidatingAdmissionPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicy build() {
      ValidatingAdmissionPolicy buildable = new ValidatingAdmissionPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
