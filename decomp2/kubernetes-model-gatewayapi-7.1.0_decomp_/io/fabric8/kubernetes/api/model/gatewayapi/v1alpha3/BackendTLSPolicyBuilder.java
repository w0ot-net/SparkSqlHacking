package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendTLSPolicyBuilder extends BackendTLSPolicyFluent implements VisitableBuilder {
   BackendTLSPolicyFluent fluent;

   public BackendTLSPolicyBuilder() {
      this(new BackendTLSPolicy());
   }

   public BackendTLSPolicyBuilder(BackendTLSPolicyFluent fluent) {
      this(fluent, new BackendTLSPolicy());
   }

   public BackendTLSPolicyBuilder(BackendTLSPolicyFluent fluent, BackendTLSPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendTLSPolicyBuilder(BackendTLSPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendTLSPolicy build() {
      BackendTLSPolicy buildable = new BackendTLSPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
