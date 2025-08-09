package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendLBPolicyListBuilder extends BackendLBPolicyListFluent implements VisitableBuilder {
   BackendLBPolicyListFluent fluent;

   public BackendLBPolicyListBuilder() {
      this(new BackendLBPolicyList());
   }

   public BackendLBPolicyListBuilder(BackendLBPolicyListFluent fluent) {
      this(fluent, new BackendLBPolicyList());
   }

   public BackendLBPolicyListBuilder(BackendLBPolicyListFluent fluent, BackendLBPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendLBPolicyListBuilder(BackendLBPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendLBPolicyList build() {
      BackendLBPolicyList buildable = new BackendLBPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
