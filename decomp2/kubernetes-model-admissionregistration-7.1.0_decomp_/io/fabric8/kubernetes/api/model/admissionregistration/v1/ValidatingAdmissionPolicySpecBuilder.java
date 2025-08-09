package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicySpecBuilder extends ValidatingAdmissionPolicySpecFluent implements VisitableBuilder {
   ValidatingAdmissionPolicySpecFluent fluent;

   public ValidatingAdmissionPolicySpecBuilder() {
      this(new ValidatingAdmissionPolicySpec());
   }

   public ValidatingAdmissionPolicySpecBuilder(ValidatingAdmissionPolicySpecFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicySpec());
   }

   public ValidatingAdmissionPolicySpecBuilder(ValidatingAdmissionPolicySpecFluent fluent, ValidatingAdmissionPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicySpecBuilder(ValidatingAdmissionPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicySpec build() {
      ValidatingAdmissionPolicySpec buildable = new ValidatingAdmissionPolicySpec(this.fluent.buildAuditAnnotations(), this.fluent.getFailurePolicy(), this.fluent.buildMatchConditions(), this.fluent.buildMatchConstraints(), this.fluent.buildParamKind(), this.fluent.buildValidations(), this.fluent.buildVariables());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
