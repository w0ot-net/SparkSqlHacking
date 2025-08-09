package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationStatusBuilder extends PriorityLevelConfigurationStatusFluent implements VisitableBuilder {
   PriorityLevelConfigurationStatusFluent fluent;

   public PriorityLevelConfigurationStatusBuilder() {
      this(new PriorityLevelConfigurationStatus());
   }

   public PriorityLevelConfigurationStatusBuilder(PriorityLevelConfigurationStatusFluent fluent) {
      this(fluent, new PriorityLevelConfigurationStatus());
   }

   public PriorityLevelConfigurationStatusBuilder(PriorityLevelConfigurationStatusFluent fluent, PriorityLevelConfigurationStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationStatusBuilder(PriorityLevelConfigurationStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfigurationStatus build() {
      PriorityLevelConfigurationStatus buildable = new PriorityLevelConfigurationStatus(this.fluent.buildConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
