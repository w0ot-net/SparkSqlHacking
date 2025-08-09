package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetConditionBuilder extends StatefulSetConditionFluent implements VisitableBuilder {
   StatefulSetConditionFluent fluent;

   public StatefulSetConditionBuilder() {
      this(new StatefulSetCondition());
   }

   public StatefulSetConditionBuilder(StatefulSetConditionFluent fluent) {
      this(fluent, new StatefulSetCondition());
   }

   public StatefulSetConditionBuilder(StatefulSetConditionFluent fluent, StatefulSetCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetConditionBuilder(StatefulSetCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetCondition build() {
      StatefulSetCondition buildable = new StatefulSetCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
