package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicyBindingListBuilder extends MutatingAdmissionPolicyBindingListFluent implements VisitableBuilder {
   MutatingAdmissionPolicyBindingListFluent fluent;

   public MutatingAdmissionPolicyBindingListBuilder() {
      this(new MutatingAdmissionPolicyBindingList());
   }

   public MutatingAdmissionPolicyBindingListBuilder(MutatingAdmissionPolicyBindingListFluent fluent) {
      this(fluent, new MutatingAdmissionPolicyBindingList());
   }

   public MutatingAdmissionPolicyBindingListBuilder(MutatingAdmissionPolicyBindingListFluent fluent, MutatingAdmissionPolicyBindingList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBindingListBuilder(MutatingAdmissionPolicyBindingList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBindingList build() {
      MutatingAdmissionPolicyBindingList buildable = new MutatingAdmissionPolicyBindingList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
