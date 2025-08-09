package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicySpecBuilder extends MutatingAdmissionPolicySpecFluent implements VisitableBuilder {
   MutatingAdmissionPolicySpecFluent fluent;

   public MutatingAdmissionPolicySpecBuilder() {
      this(new MutatingAdmissionPolicySpec());
   }

   public MutatingAdmissionPolicySpecBuilder(MutatingAdmissionPolicySpecFluent fluent) {
      this(fluent, new MutatingAdmissionPolicySpec());
   }

   public MutatingAdmissionPolicySpecBuilder(MutatingAdmissionPolicySpecFluent fluent, MutatingAdmissionPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicySpecBuilder(MutatingAdmissionPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicySpec build() {
      MutatingAdmissionPolicySpec buildable = new MutatingAdmissionPolicySpec(this.fluent.getFailurePolicy(), this.fluent.buildMatchConditions(), this.fluent.buildMatchConstraints(), this.fluent.buildMutations(), this.fluent.buildParamKind(), this.fluent.getReinvocationPolicy(), this.fluent.buildVariables());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
