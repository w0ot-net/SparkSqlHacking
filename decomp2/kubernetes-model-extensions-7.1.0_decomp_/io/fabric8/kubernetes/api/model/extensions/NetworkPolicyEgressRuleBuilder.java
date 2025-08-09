package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyEgressRuleBuilder extends NetworkPolicyEgressRuleFluent implements VisitableBuilder {
   NetworkPolicyEgressRuleFluent fluent;

   public NetworkPolicyEgressRuleBuilder() {
      this(new NetworkPolicyEgressRule());
   }

   public NetworkPolicyEgressRuleBuilder(NetworkPolicyEgressRuleFluent fluent) {
      this(fluent, new NetworkPolicyEgressRule());
   }

   public NetworkPolicyEgressRuleBuilder(NetworkPolicyEgressRuleFluent fluent, NetworkPolicyEgressRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyEgressRuleBuilder(NetworkPolicyEgressRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicyEgressRule build() {
      NetworkPolicyEgressRule buildable = new NetworkPolicyEgressRule(this.fluent.buildPorts(), this.fluent.buildTo());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
