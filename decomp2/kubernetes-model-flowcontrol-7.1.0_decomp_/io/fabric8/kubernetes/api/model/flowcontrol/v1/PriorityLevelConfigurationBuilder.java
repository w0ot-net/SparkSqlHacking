package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationBuilder extends PriorityLevelConfigurationFluent implements VisitableBuilder {
   PriorityLevelConfigurationFluent fluent;

   public PriorityLevelConfigurationBuilder() {
      this(new PriorityLevelConfiguration());
   }

   public PriorityLevelConfigurationBuilder(PriorityLevelConfigurationFluent fluent) {
      this(fluent, new PriorityLevelConfiguration());
   }

   public PriorityLevelConfigurationBuilder(PriorityLevelConfigurationFluent fluent, PriorityLevelConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationBuilder(PriorityLevelConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfiguration build() {
      PriorityLevelConfiguration buildable = new PriorityLevelConfiguration(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
