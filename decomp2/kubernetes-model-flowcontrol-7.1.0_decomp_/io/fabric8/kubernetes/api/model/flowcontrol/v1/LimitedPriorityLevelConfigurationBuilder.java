package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LimitedPriorityLevelConfigurationBuilder extends LimitedPriorityLevelConfigurationFluent implements VisitableBuilder {
   LimitedPriorityLevelConfigurationFluent fluent;

   public LimitedPriorityLevelConfigurationBuilder() {
      this(new LimitedPriorityLevelConfiguration());
   }

   public LimitedPriorityLevelConfigurationBuilder(LimitedPriorityLevelConfigurationFluent fluent) {
      this(fluent, new LimitedPriorityLevelConfiguration());
   }

   public LimitedPriorityLevelConfigurationBuilder(LimitedPriorityLevelConfigurationFluent fluent, LimitedPriorityLevelConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LimitedPriorityLevelConfigurationBuilder(LimitedPriorityLevelConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LimitedPriorityLevelConfiguration build() {
      LimitedPriorityLevelConfiguration buildable = new LimitedPriorityLevelConfiguration(this.fluent.getBorrowingLimitPercent(), this.fluent.getLendablePercent(), this.fluent.buildLimitResponse(), this.fluent.getNominalConcurrencyShares());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
