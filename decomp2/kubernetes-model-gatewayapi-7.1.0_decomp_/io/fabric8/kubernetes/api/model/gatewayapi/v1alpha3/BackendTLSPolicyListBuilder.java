package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendTLSPolicyListBuilder extends BackendTLSPolicyListFluent implements VisitableBuilder {
   BackendTLSPolicyListFluent fluent;

   public BackendTLSPolicyListBuilder() {
      this(new BackendTLSPolicyList());
   }

   public BackendTLSPolicyListBuilder(BackendTLSPolicyListFluent fluent) {
      this(fluent, new BackendTLSPolicyList());
   }

   public BackendTLSPolicyListBuilder(BackendTLSPolicyListFluent fluent, BackendTLSPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendTLSPolicyListBuilder(BackendTLSPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendTLSPolicyList build() {
      BackendTLSPolicyList buildable = new BackendTLSPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
