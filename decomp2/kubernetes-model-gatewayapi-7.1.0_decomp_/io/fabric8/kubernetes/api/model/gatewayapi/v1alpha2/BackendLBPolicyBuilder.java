package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendLBPolicyBuilder extends BackendLBPolicyFluent implements VisitableBuilder {
   BackendLBPolicyFluent fluent;

   public BackendLBPolicyBuilder() {
      this(new BackendLBPolicy());
   }

   public BackendLBPolicyBuilder(BackendLBPolicyFluent fluent) {
      this(fluent, new BackendLBPolicy());
   }

   public BackendLBPolicyBuilder(BackendLBPolicyFluent fluent, BackendLBPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendLBPolicyBuilder(BackendLBPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendLBPolicy build() {
      BackendLBPolicy buildable = new BackendLBPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
