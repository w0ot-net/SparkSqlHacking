package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AggregationRuleBuilder extends AggregationRuleFluent implements VisitableBuilder {
   AggregationRuleFluent fluent;

   public AggregationRuleBuilder() {
      this(new AggregationRule());
   }

   public AggregationRuleBuilder(AggregationRuleFluent fluent) {
      this(fluent, new AggregationRule());
   }

   public AggregationRuleBuilder(AggregationRuleFluent fluent, AggregationRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AggregationRuleBuilder(AggregationRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AggregationRule build() {
      AggregationRule buildable = new AggregationRule(this.fluent.buildClusterRoleSelectors());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
