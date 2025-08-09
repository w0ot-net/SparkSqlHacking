package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ValidatingAdmissionPolicyBindingListBuilder extends ValidatingAdmissionPolicyBindingListFluent implements VisitableBuilder {
   ValidatingAdmissionPolicyBindingListFluent fluent;

   public ValidatingAdmissionPolicyBindingListBuilder() {
      this(new ValidatingAdmissionPolicyBindingList());
   }

   public ValidatingAdmissionPolicyBindingListBuilder(ValidatingAdmissionPolicyBindingListFluent fluent) {
      this(fluent, new ValidatingAdmissionPolicyBindingList());
   }

   public ValidatingAdmissionPolicyBindingListBuilder(ValidatingAdmissionPolicyBindingListFluent fluent, ValidatingAdmissionPolicyBindingList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBindingListBuilder(ValidatingAdmissionPolicyBindingList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ValidatingAdmissionPolicyBindingList build() {
      ValidatingAdmissionPolicyBindingList buildable = new ValidatingAdmissionPolicyBindingList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
