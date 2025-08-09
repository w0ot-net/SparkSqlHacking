package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationConditionBuilder extends PriorityLevelConfigurationConditionFluent implements VisitableBuilder {
   PriorityLevelConfigurationConditionFluent fluent;

   public PriorityLevelConfigurationConditionBuilder() {
      this(new PriorityLevelConfigurationCondition());
   }

   public PriorityLevelConfigurationConditionBuilder(PriorityLevelConfigurationConditionFluent fluent) {
      this(fluent, new PriorityLevelConfigurationCondition());
   }

   public PriorityLevelConfigurationConditionBuilder(PriorityLevelConfigurationConditionFluent fluent, PriorityLevelConfigurationCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationConditionBuilder(PriorityLevelConfigurationCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfigurationCondition build() {
      PriorityLevelConfigurationCondition buildable = new PriorityLevelConfigurationCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
