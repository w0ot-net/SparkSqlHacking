package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendLBPolicySpecBuilder extends BackendLBPolicySpecFluent implements VisitableBuilder {
   BackendLBPolicySpecFluent fluent;

   public BackendLBPolicySpecBuilder() {
      this(new BackendLBPolicySpec());
   }

   public BackendLBPolicySpecBuilder(BackendLBPolicySpecFluent fluent) {
      this(fluent, new BackendLBPolicySpec());
   }

   public BackendLBPolicySpecBuilder(BackendLBPolicySpecFluent fluent, BackendLBPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendLBPolicySpecBuilder(BackendLBPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendLBPolicySpec build() {
      BackendLBPolicySpec buildable = new BackendLBPolicySpec(this.fluent.buildSessionPersistence(), this.fluent.buildTargetRefs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
