package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyBuilder extends NetworkPolicyFluent implements VisitableBuilder {
   NetworkPolicyFluent fluent;

   public NetworkPolicyBuilder() {
      this(new NetworkPolicy());
   }

   public NetworkPolicyBuilder(NetworkPolicyFluent fluent) {
      this(fluent, new NetworkPolicy());
   }

   public NetworkPolicyBuilder(NetworkPolicyFluent fluent, NetworkPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyBuilder(NetworkPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicy build() {
      NetworkPolicy buildable = new NetworkPolicy(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
