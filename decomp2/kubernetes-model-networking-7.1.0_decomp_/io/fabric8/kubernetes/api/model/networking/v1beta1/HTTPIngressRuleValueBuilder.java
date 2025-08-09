package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPIngressRuleValueBuilder extends HTTPIngressRuleValueFluent implements VisitableBuilder {
   HTTPIngressRuleValueFluent fluent;

   public HTTPIngressRuleValueBuilder() {
      this(new HTTPIngressRuleValue());
   }

   public HTTPIngressRuleValueBuilder(HTTPIngressRuleValueFluent fluent) {
      this(fluent, new HTTPIngressRuleValue());
   }

   public HTTPIngressRuleValueBuilder(HTTPIngressRuleValueFluent fluent, HTTPIngressRuleValue instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPIngressRuleValueBuilder(HTTPIngressRuleValue instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPIngressRuleValue build() {
      HTTPIngressRuleValue buildable = new HTTPIngressRuleValue(this.fluent.buildPaths());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
