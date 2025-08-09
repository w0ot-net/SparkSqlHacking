package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ComponentStatusListBuilder extends ComponentStatusListFluent implements VisitableBuilder {
   ComponentStatusListFluent fluent;

   public ComponentStatusListBuilder() {
      this(new ComponentStatusList());
   }

   public ComponentStatusListBuilder(ComponentStatusListFluent fluent) {
      this(fluent, new ComponentStatusList());
   }

   public ComponentStatusListBuilder(ComponentStatusListFluent fluent, ComponentStatusList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ComponentStatusListBuilder(ComponentStatusList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ComponentStatusList build() {
      ComponentStatusList buildable = new ComponentStatusList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
