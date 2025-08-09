package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyListBuilder extends ValidatingAdmissionPolicyListFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyListFluent fluent;

   public ValidatingAdmissionPolicyListBuilder() {
      this(new ValidatingAdmissionPolicyList());
   }

   public ValidatingAdmissionPolicyListBuilder(ValidatingAdmissionPolicyListFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicyList());
   }

   public ValidatingAdmissionPolicyListBuilder(ValidatingAdmissionPolicyListFluent fluent, ValidatingAdmissionPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyListBuilder(ValidatingAdmissionPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyList build() {
      ValidatingAdmissionPolicyList buildable = new ValidatingAdmissionPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
