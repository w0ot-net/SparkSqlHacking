package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyBindingSpecBuilder extends ValidatingAdmissionPolicyBindingSpecFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyBindingSpecFluent fluent;

   public ValidatingAdmissionPolicyBindingSpecBuilder() {
      this(new ValidatingAdmissionPolicyBindingSpec());
   }

   public ValidatingAdmissionPolicyBindingSpecBuilder(ValidatingAdmissionPolicyBindingSpecFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicyBindingSpec());
   }

   public ValidatingAdmissionPolicyBindingSpecBuilder(ValidatingAdmissionPolicyBindingSpecFluent fluent, ValidatingAdmissionPolicyBindingSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBindingSpecBuilder(ValidatingAdmissionPolicyBindingSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBindingSpec build() {
      ValidatingAdmissionPolicyBindingSpec buildable = new ValidatingAdmissionPolicyBindingSpec(this.fluent.buildMatchResources(), this.fluent.buildParamRef(), this.fluent.getPolicyName(), this.fluent.getValidationActions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
