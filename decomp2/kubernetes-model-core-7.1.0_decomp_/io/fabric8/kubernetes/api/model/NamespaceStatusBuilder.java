package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespaceStatusBuilder extends NamespaceStatusFluent implements VisitableBuilder {
   NamespaceStatusFluent fluent;

   public NamespaceStatusBuilder() {
      this(new NamespaceStatus());
   }

   public NamespaceStatusBuilder(NamespaceStatusFluent fluent) {
      this(fluent, new NamespaceStatus());
   }

   public NamespaceStatusBuilder(NamespaceStatusFluent fluent, NamespaceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespaceStatusBuilder(NamespaceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamespaceStatus build() {
      NamespaceStatus buildable = new NamespaceStatus(this.fluent.buildConditions(), this.fluent.getPhase());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
