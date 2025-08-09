package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ExemptPriorityLevelConfigurationBuilder extends ExemptPriorityLevelConfigurationFluent implements VisitableBuilder {
   ExemptPriorityLevelConfigurationFluent fluent;

   public ExemptPriorityLevelConfigurationBuilder() {
      this(new ExemptPriorityLevelConfiguration());
   }

   public ExemptPriorityLevelConfigurationBuilder(ExemptPriorityLevelConfigurationFluent fluent) {
      this(fluent, new ExemptPriorityLevelConfiguration());
   }

   public ExemptPriorityLevelConfigurationBuilder(ExemptPriorityLevelConfigurationFluent fluent, ExemptPriorityLevelConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ExemptPriorityLevelConfigurationBuilder(ExemptPriorityLevelConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ExemptPriorityLevelConfiguration build() {
      ExemptPriorityLevelConfiguration buildable = new ExemptPriorityLevelConfiguration(this.fluent.getLendablePercent(), this.fluent.getNominalConcurrencyShares());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
