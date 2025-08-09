package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuleBuilder extends RuleFluent implements VisitableBuilder {
   RuleFluent fluent;

   public RuleBuilder() {
      this(new Rule());
   }

   public RuleBuilder(RuleFluent fluent) {
      this(fluent, new Rule());
   }

   public RuleBuilder(RuleFluent fluent, Rule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuleBuilder(Rule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Rule build() {
      Rule buildable = new Rule(this.fluent.getApiGroups(), this.fluent.getApiVersions(), this.fluent.getResources(), this.fluent.getScope());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
