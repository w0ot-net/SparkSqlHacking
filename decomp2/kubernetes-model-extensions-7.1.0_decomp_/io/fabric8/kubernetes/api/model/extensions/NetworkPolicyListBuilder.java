package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyListBuilder extends NetworkPolicyListFluent implements VisitableBuilder {
   NetworkPolicyListFluent fluent;

   public NetworkPolicyListBuilder() {
      this(new NetworkPolicyList());
   }

   public NetworkPolicyListBuilder(NetworkPolicyListFluent fluent) {
      this(fluent, new NetworkPolicyList());
   }

   public NetworkPolicyListBuilder(NetworkPolicyListFluent fluent, NetworkPolicyList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyListBuilder(NetworkPolicyList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicyList build() {
      NetworkPolicyList buildable = new NetworkPolicyList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
