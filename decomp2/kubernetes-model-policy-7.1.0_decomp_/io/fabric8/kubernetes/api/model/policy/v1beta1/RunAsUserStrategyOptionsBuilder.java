package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RunAsUserStrategyOptionsBuilder extends RunAsUserStrategyOptionsFluent implements VisitableBuilder {
   RunAsUserStrategyOptionsFluent fluent;

   public RunAsUserStrategyOptionsBuilder() {
      this(new RunAsUserStrategyOptions());
   }

   public RunAsUserStrategyOptionsBuilder(RunAsUserStrategyOptionsFluent fluent) {
      this(fluent, new RunAsUserStrategyOptions());
   }

   public RunAsUserStrategyOptionsBuilder(RunAsUserStrategyOptionsFluent fluent, RunAsUserStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RunAsUserStrategyOptionsBuilder(RunAsUserStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RunAsUserStrategyOptions build() {
      RunAsUserStrategyOptions buildable = new RunAsUserStrategyOptions(this.fluent.buildRanges(), this.fluent.getRule());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
