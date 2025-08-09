package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ComponentConditionBuilder extends ComponentConditionFluent implements VisitableBuilder {
   ComponentConditionFluent fluent;

   public ComponentConditionBuilder() {
      this(new ComponentCondition());
   }

   public ComponentConditionBuilder(ComponentConditionFluent fluent) {
      this(fluent, new ComponentCondition());
   }

   public ComponentConditionBuilder(ComponentConditionFluent fluent, ComponentCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ComponentConditionBuilder(ComponentCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ComponentCondition build() {
      ComponentCondition buildable = new ComponentCondition(this.fluent.getError(), this.fluent.getMessage(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
