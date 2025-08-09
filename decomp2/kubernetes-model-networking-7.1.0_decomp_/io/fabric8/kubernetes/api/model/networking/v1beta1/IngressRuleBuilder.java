package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressRuleBuilder extends IngressRuleFluent implements VisitableBuilder {
   IngressRuleFluent fluent;

   public IngressRuleBuilder() {
      this(new IngressRule());
   }

   public IngressRuleBuilder(IngressRuleFluent fluent) {
      this(fluent, new IngressRule());
   }

   public IngressRuleBuilder(IngressRuleFluent fluent, IngressRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressRuleBuilder(IngressRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressRule build() {
      IngressRule buildable = new IngressRule(this.fluent.getHost(), this.fluent.buildHttp());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
