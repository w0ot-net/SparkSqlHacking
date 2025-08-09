package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SupplementalGroupsStrategyOptionsBuilder extends SupplementalGroupsStrategyOptionsFluent implements VisitableBuilder {
   SupplementalGroupsStrategyOptionsFluent fluent;

   public SupplementalGroupsStrategyOptionsBuilder() {
      this(new SupplementalGroupsStrategyOptions());
   }

   public SupplementalGroupsStrategyOptionsBuilder(SupplementalGroupsStrategyOptionsFluent fluent) {
      this(fluent, new SupplementalGroupsStrategyOptions());
   }

   public SupplementalGroupsStrategyOptionsBuilder(SupplementalGroupsStrategyOptionsFluent fluent, SupplementalGroupsStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SupplementalGroupsStrategyOptionsBuilder(SupplementalGroupsStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SupplementalGroupsStrategyOptions build() {
      SupplementalGroupsStrategyOptions buildable = new SupplementalGroupsStrategyOptions(this.fluent.buildRanges(), this.fluent.getRule());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
