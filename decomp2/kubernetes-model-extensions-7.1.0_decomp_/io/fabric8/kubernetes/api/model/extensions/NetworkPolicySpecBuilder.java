package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicySpecBuilder extends NetworkPolicySpecFluent implements VisitableBuilder {
   NetworkPolicySpecFluent fluent;

   public NetworkPolicySpecBuilder() {
      this(new NetworkPolicySpec());
   }

   public NetworkPolicySpecBuilder(NetworkPolicySpecFluent fluent) {
      this(fluent, new NetworkPolicySpec());
   }

   public NetworkPolicySpecBuilder(NetworkPolicySpecFluent fluent, NetworkPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicySpecBuilder(NetworkPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicySpec build() {
      NetworkPolicySpec buildable = new NetworkPolicySpec(this.fluent.buildEgress(), this.fluent.buildIngress(), this.fluent.buildPodSelector(), this.fluent.getPolicyTypes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
