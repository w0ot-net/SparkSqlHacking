package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MatchConditionBuilder extends MatchConditionFluent implements VisitableBuilder {
   MatchConditionFluent fluent;

   public MatchConditionBuilder() {
      this(new MatchCondition());
   }

   public MatchConditionBuilder(MatchConditionFluent fluent) {
      this(fluent, new MatchCondition());
   }

   public MatchConditionBuilder(MatchConditionFluent fluent, MatchCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MatchConditionBuilder(MatchCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MatchCondition build() {
      MatchCondition buildable = new MatchCondition(this.fluent.getExpression(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
