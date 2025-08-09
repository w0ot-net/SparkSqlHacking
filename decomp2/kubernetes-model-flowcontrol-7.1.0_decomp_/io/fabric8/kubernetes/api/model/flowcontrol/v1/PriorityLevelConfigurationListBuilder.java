package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationListBuilder extends PriorityLevelConfigurationListFluent implements VisitableBuilder {
   PriorityLevelConfigurationListFluent fluent;

   public PriorityLevelConfigurationListBuilder() {
      this(new PriorityLevelConfigurationList());
   }

   public PriorityLevelConfigurationListBuilder(PriorityLevelConfigurationListFluent fluent) {
      this(fluent, new PriorityLevelConfigurationList());
   }

   public PriorityLevelConfigurationListBuilder(PriorityLevelConfigurationListFluent fluent, PriorityLevelConfigurationList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationListBuilder(PriorityLevelConfigurationList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfigurationList build() {
      PriorityLevelConfigurationList buildable = new PriorityLevelConfigurationList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
