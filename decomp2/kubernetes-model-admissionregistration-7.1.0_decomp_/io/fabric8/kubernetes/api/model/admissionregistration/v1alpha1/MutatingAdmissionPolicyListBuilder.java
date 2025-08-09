package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicyListBuilder extends MutatingAdmissionPolicyListFluent implements VisitableBuilder {
   MutatingAdmissionPolicyListFluent fluent;

   public MutatingAdmissionPolicyListBuilder() {
      this(new MutatingAdmissionPolicyList());
   }

   public MutatingAdmissionPolicyListBuilder(MutatingAdmissionPolicyListFluent fluent) {
      this(fluent, new MutatingAdmissionPolicyList());
   }

   public MutatingAdmissionPolicyListBuilder(MutatingAdmissionPolicyListFluent fluent, MutatingAdmissionPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicyListBuilder(MutatingAdmissionPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicyList build() {
      MutatingAdmissionPolicyList buildable = new MutatingAdmissionPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
