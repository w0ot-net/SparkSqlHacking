package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SleepActionBuilder extends SleepActionFluent implements VisitableBuilder {
   SleepActionFluent fluent;

   public SleepActionBuilder() {
      this(new SleepAction());
   }

   public SleepActionBuilder(SleepActionFluent fluent) {
      this(fluent, new SleepAction());
   }

   public SleepActionBuilder(SleepActionFluent fluent, SleepAction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SleepActionBuilder(SleepAction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SleepAction build() {
      SleepAction buildable = new SleepAction(this.fluent.getSeconds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
