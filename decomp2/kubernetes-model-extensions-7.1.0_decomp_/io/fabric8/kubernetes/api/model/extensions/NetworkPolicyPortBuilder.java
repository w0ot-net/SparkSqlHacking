package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyPortBuilder extends NetworkPolicyPortFluent implements VisitableBuilder {
   NetworkPolicyPortFluent fluent;

   public NetworkPolicyPortBuilder() {
      this(new NetworkPolicyPort());
   }

   public NetworkPolicyPortBuilder(NetworkPolicyPortFluent fluent) {
      this(fluent, new NetworkPolicyPort());
   }

   public NetworkPolicyPortBuilder(NetworkPolicyPortFluent fluent, NetworkPolicyPort instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyPortBuilder(NetworkPolicyPort instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicyPort build() {
      NetworkPolicyPort buildable = new NetworkPolicyPort(this.fluent.buildPort(), this.fluent.getProtocol());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
