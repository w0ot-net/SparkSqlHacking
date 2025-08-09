package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceRuleBuilder extends ResourceRuleFluent implements VisitableBuilder {
   ResourceRuleFluent fluent;

   public ResourceRuleBuilder() {
      this(new ResourceRule());
   }

   public ResourceRuleBuilder(ResourceRuleFluent fluent) {
      this(fluent, new ResourceRule());
   }

   public ResourceRuleBuilder(ResourceRuleFluent fluent, ResourceRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceRuleBuilder(ResourceRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceRule build() {
      ResourceRule buildable = new ResourceRule(this.fluent.getApiGroups(), this.fluent.getResourceNames(), this.fluent.getResources(), this.fluent.getVerbs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
