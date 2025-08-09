package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkPolicyIngressRuleBuilder extends NetworkPolicyIngressRuleFluent implements VisitableBuilder {
   NetworkPolicyIngressRuleFluent fluent;

   public NetworkPolicyIngressRuleBuilder() {
      this(new NetworkPolicyIngressRule());
   }

   public NetworkPolicyIngressRuleBuilder(NetworkPolicyIngressRuleFluent fluent) {
      this(fluent, new NetworkPolicyIngressRule());
   }

   public NetworkPolicyIngressRuleBuilder(NetworkPolicyIngressRuleFluent fluent, NetworkPolicyIngressRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkPolicyIngressRuleBuilder(NetworkPolicyIngressRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkPolicyIngressRule build() {
      NetworkPolicyIngressRule buildable = new NetworkPolicyIngressRule(this.fluent.buildFrom(), this.fluent.buildPorts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
