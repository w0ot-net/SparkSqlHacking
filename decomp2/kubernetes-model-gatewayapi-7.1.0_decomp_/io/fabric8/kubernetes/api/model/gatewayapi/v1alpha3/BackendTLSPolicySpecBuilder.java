package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendTLSPolicySpecBuilder extends BackendTLSPolicySpecFluent implements VisitableBuilder {
   BackendTLSPolicySpecFluent fluent;

   public BackendTLSPolicySpecBuilder() {
      this(new BackendTLSPolicySpec());
   }

   public BackendTLSPolicySpecBuilder(BackendTLSPolicySpecFluent fluent) {
      this(fluent, new BackendTLSPolicySpec());
   }

   public BackendTLSPolicySpecBuilder(BackendTLSPolicySpecFluent fluent, BackendTLSPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendTLSPolicySpecBuilder(BackendTLSPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendTLSPolicySpec build() {
      BackendTLSPolicySpec buildable = new BackendTLSPolicySpec(this.fluent.getOptions(), this.fluent.buildTargetRefs(), this.fluent.buildValidation());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
