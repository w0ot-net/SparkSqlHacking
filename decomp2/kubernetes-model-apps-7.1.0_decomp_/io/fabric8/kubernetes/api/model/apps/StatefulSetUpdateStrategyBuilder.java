package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetUpdateStrategyBuilder extends StatefulSetUpdateStrategyFluent implements VisitableBuilder {
   StatefulSetUpdateStrategyFluent fluent;

   public StatefulSetUpdateStrategyBuilder() {
      this(new StatefulSetUpdateStrategy());
   }

   public StatefulSetUpdateStrategyBuilder(StatefulSetUpdateStrategyFluent fluent) {
      this(fluent, new StatefulSetUpdateStrategy());
   }

   public StatefulSetUpdateStrategyBuilder(StatefulSetUpdateStrategyFluent fluent, StatefulSetUpdateStrategy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetUpdateStrategyBuilder(StatefulSetUpdateStrategy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetUpdateStrategy build() {
      StatefulSetUpdateStrategy buildable = new StatefulSetUpdateStrategy(this.fluent.buildRollingUpdate(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
