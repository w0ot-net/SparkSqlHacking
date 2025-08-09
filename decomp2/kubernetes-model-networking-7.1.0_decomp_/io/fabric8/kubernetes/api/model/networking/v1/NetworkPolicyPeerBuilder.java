package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyPeerBuilder extends NetworkPolicyPeerFluent implements VisitableBuilder {
   NetworkPolicyPeerFluent fluent;

   public NetworkPolicyPeerBuilder() {
      this(new NetworkPolicyPeer());
   }

   public NetworkPolicyPeerBuilder(NetworkPolicyPeerFluent fluent) {
      this(fluent, new NetworkPolicyPeer());
   }

   public NetworkPolicyPeerBuilder(NetworkPolicyPeerFluent fluent, NetworkPolicyPeer instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyPeerBuilder(NetworkPolicyPeer instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicyPeer build() {
      NetworkPolicyPeer buildable = new NetworkPolicyPeer(this.fluent.buildIpBlock(), this.fluent.buildNamespaceSelector(), this.fluent.buildPodSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
