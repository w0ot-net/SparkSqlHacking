package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicyBindingSpecBuilder extends MutatingAdmissionPolicyBindingSpecFluent implements VisitableBuilder {
   MutatingAdmissionPolicyBindingSpecFluent fluent;

   public MutatingAdmissionPolicyBindingSpecBuilder() {
      this(new MutatingAdmissionPolicyBindingSpec());
   }

   public MutatingAdmissionPolicyBindingSpecBuilder(MutatingAdmissionPolicyBindingSpecFluent fluent) {
      this(fluent, new MutatingAdmissionPolicyBindingSpec());
   }

   public MutatingAdmissionPolicyBindingSpecBuilder(MutatingAdmissionPolicyBindingSpecFluent fluent, MutatingAdmissionPolicyBindingSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBindingSpecBuilder(MutatingAdmissionPolicyBindingSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBindingSpec build() {
      MutatingAdmissionPolicyBindingSpec buildable = new MutatingAdmissionPolicyBindingSpec(this.fluent.buildMatchResources(), this.fluent.buildParamRef(), this.fluent.getPolicyName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
