package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ComponentStatusBuilder extends ComponentStatusFluent implements VisitableBuilder {
   ComponentStatusFluent fluent;

   public ComponentStatusBuilder() {
      this(new ComponentStatus());
   }

   public ComponentStatusBuilder(ComponentStatusFluent fluent) {
      this(fluent, new ComponentStatus());
   }

   public ComponentStatusBuilder(ComponentStatusFluent fluent, ComponentStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ComponentStatusBuilder(ComponentStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ComponentStatus build() {
      ComponentStatus buildable = new ComponentStatus(this.fluent.getApiVersion(), this.fluent.buildConditions(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
