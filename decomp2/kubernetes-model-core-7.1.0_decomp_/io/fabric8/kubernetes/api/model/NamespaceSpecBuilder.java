package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespaceSpecBuilder extends NamespaceSpecFluent implements VisitableBuilder {
   NamespaceSpecFluent fluent;

   public NamespaceSpecBuilder() {
      this(new NamespaceSpec());
   }

   public NamespaceSpecBuilder(NamespaceSpecFluent fluent) {
      this(fluent, new NamespaceSpec());
   }

   public NamespaceSpecBuilder(NamespaceSpecFluent fluent, NamespaceSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespaceSpecBuilder(NamespaceSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamespaceSpec build() {
      NamespaceSpec buildable = new NamespaceSpec(this.fluent.getFinalizers());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
