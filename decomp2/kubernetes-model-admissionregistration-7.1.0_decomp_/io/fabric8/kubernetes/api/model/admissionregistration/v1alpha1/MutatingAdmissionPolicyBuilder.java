package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutatingAdmissionPolicyBuilder extends MutatingAdmissionPolicyFluent implements VisitableBuilder {
   MutatingAdmissionPolicyFluent fluent;

   public MutatingAdmissionPolicyBuilder() {
      this(new MutatingAdmissionPolicy());
   }

   public MutatingAdmissionPolicyBuilder(MutatingAdmissionPolicyFluent fluent) {
      this(fluent, new MutatingAdmissionPolicy());
   }

   public MutatingAdmissionPolicyBuilder(MutatingAdmissionPolicyFluent fluent, MutatingAdmissionPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutatingAdmissionPolicyBuilder(MutatingAdmissionPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MutatingAdmissionPolicy build() {
      MutatingAdmissionPolicy buildable = new MutatingAdmissionPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
