package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RunAsGroupStrategyOptionsBuilder extends RunAsGroupStrategyOptionsFluent implements VisitableBuilder {
   RunAsGroupStrategyOptionsFluent fluent;

   public RunAsGroupStrategyOptionsBuilder() {
      this(new RunAsGroupStrategyOptions());
   }

   public RunAsGroupStrategyOptionsBuilder(RunAsGroupStrategyOptionsFluent fluent) {
      this(fluent, new RunAsGroupStrategyOptions());
   }

   public RunAsGroupStrategyOptionsBuilder(RunAsGroupStrategyOptionsFluent fluent, RunAsGroupStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RunAsGroupStrategyOptionsBuilder(RunAsGroupStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RunAsGroupStrategyOptions build() {
      RunAsGroupStrategyOptions buildable = new RunAsGroupStrategyOptions(this.fluent.buildRanges(), this.fluent.getRule());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
