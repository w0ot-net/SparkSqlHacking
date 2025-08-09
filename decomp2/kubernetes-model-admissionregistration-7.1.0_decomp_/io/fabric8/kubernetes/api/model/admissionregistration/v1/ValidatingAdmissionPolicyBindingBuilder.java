package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyBindingBuilder extends ValidatingAdmissionPolicyBindingFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyBindingFluent fluent;

   public ValidatingAdmissionPolicyBindingBuilder() {
      this(new ValidatingAdmissionPolicyBinding());
   }

   public ValidatingAdmissionPolicyBindingBuilder(ValidatingAdmissionPolicyBindingFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicyBinding());
   }

   public ValidatingAdmissionPolicyBindingBuilder(ValidatingAdmissionPolicyBindingFluent fluent, ValidatingAdmissionPolicyBinding instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBindingBuilder(ValidatingAdmissionPolicyBinding instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBinding build() {
      ValidatingAdmissionPolicyBinding buildable = new ValidatingAdmissionPolicyBinding(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
