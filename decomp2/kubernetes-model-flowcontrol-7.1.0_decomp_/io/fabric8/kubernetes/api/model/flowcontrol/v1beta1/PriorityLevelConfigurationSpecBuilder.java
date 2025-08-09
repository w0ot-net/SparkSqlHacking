package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationSpecBuilder extends PriorityLevelConfigurationSpecFluent implements VisitableBuilder {
   PriorityLevelConfigurationSpecFluent fluent;

   public PriorityLevelConfigurationSpecBuilder() {
      this(new PriorityLevelConfigurationSpec());
   }

   public PriorityLevelConfigurationSpecBuilder(PriorityLevelConfigurationSpecFluent fluent) {
      this(fluent, new PriorityLevelConfigurationSpec());
   }

   public PriorityLevelConfigurationSpecBuilder(PriorityLevelConfigurationSpecFluent fluent, PriorityLevelConfigurationSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationSpecBuilder(PriorityLevelConfigurationSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfigurationSpec build() {
      PriorityLevelConfigurationSpec buildable = new PriorityLevelConfigurationSpec(this.fluent.buildLimited(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
