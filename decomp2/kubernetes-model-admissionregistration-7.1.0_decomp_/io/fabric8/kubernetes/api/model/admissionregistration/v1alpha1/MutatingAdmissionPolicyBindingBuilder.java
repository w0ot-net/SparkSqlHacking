package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicyBindingBuilder extends MutatingAdmissionPolicyBindingFluent implements VisitableBuilder {
   MutatingAdmissionPolicyBindingFluent fluent;

   public MutatingAdmissionPolicyBindingBuilder() {
      this(new MutatingAdmissionPolicyBinding());
   }

   public MutatingAdmissionPolicyBindingBuilder(MutatingAdmissionPolicyBindingFluent fluent) {
      this(fluent, new MutatingAdmissionPolicyBinding());
   }

   public MutatingAdmissionPolicyBindingBuilder(MutatingAdmissionPolicyBindingFluent fluent, MutatingAdmissionPolicyBinding instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBindingBuilder(MutatingAdmissionPolicyBinding instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBinding build() {
      MutatingAdmissionPolicyBinding buildable = new MutatingAdmissionPolicyBinding(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
