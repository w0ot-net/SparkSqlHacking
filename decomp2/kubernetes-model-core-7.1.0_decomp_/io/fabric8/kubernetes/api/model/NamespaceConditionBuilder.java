package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespaceConditionBuilder extends NamespaceConditionFluent implements VisitableBuilder {
   NamespaceConditionFluent fluent;

   public NamespaceConditionBuilder() {
      this(new NamespaceCondition());
   }

   public NamespaceConditionBuilder(NamespaceConditionFluent fluent) {
      this(fluent, new NamespaceCondition());
   }

   public NamespaceConditionBuilder(NamespaceConditionFluent fluent, NamespaceCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespaceConditionBuilder(NamespaceCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamespaceCondition build() {
      NamespaceCondition buildable = new NamespaceCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
