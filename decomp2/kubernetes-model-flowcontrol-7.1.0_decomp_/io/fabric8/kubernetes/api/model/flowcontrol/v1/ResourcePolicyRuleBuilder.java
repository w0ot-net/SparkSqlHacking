package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourcePolicyRuleBuilder extends ResourcePolicyRuleFluent implements VisitableBuilder {
   ResourcePolicyRuleFluent fluent;

   public ResourcePolicyRuleBuilder() {
      this(new ResourcePolicyRule());
   }

   public ResourcePolicyRuleBuilder(ResourcePolicyRuleFluent fluent) {
      this(fluent, new ResourcePolicyRule());
   }

   public ResourcePolicyRuleBuilder(ResourcePolicyRuleFluent fluent, ResourcePolicyRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourcePolicyRuleBuilder(ResourcePolicyRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourcePolicyRule build() {
      ResourcePolicyRule buildable = new ResourcePolicyRule(this.fluent.getApiGroups(), this.fluent.getClusterScope(), this.fluent.getNamespaces(), this.fluent.getResources(), this.fluent.getVerbs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
