package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RollingUpdateStatefulSetStrategyBuilder extends RollingUpdateStatefulSetStrategyFluent implements VisitableBuilder {
   RollingUpdateStatefulSetStrategyFluent fluent;

   public RollingUpdateStatefulSetStrategyBuilder() {
      this(new RollingUpdateStatefulSetStrategy());
   }

   public RollingUpdateStatefulSetStrategyBuilder(RollingUpdateStatefulSetStrategyFluent fluent) {
      this(fluent, new RollingUpdateStatefulSetStrategy());
   }

   public RollingUpdateStatefulSetStrategyBuilder(RollingUpdateStatefulSetStrategyFluent fluent, RollingUpdateStatefulSetStrategy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RollingUpdateStatefulSetStrategyBuilder(RollingUpdateStatefulSetStrategy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RollingUpdateStatefulSetStrategy build() {
      RollingUpdateStatefulSetStrategy buildable = new RollingUpdateStatefulSetStrategy(this.fluent.buildMaxUnavailable(), this.fluent.getPartition());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
