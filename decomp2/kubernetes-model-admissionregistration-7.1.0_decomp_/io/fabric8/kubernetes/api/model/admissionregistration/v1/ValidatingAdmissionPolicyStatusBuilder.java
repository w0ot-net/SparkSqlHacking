package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyStatusBuilder extends ValidatingAdmissionPolicyStatusFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyStatusFluent fluent;

   public ValidatingAdmissionPolicyStatusBuilder() {
      this(new ValidatingAdmissionPolicyStatus());
   }

   public ValidatingAdmissionPolicyStatusBuilder(ValidatingAdmissionPolicyStatusFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicyStatus());
   }

   public ValidatingAdmissionPolicyStatusBuilder(ValidatingAdmissionPolicyStatusFluent fluent, ValidatingAdmissionPolicyStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyStatusBuilder(ValidatingAdmissionPolicyStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyStatus build() {
      ValidatingAdmissionPolicyStatus buildable = new ValidatingAdmissionPolicyStatus(this.fluent.getConditions(), this.fluent.getObservedGeneration(), this.fluent.buildTypeChecking());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
