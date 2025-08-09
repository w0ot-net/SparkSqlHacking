package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedExtensionBuilder extends NamedExtensionFluent implements VisitableBuilder {
   NamedExtensionFluent fluent;

   public NamedExtensionBuilder() {
      this(new NamedExtension());
   }

   public NamedExtensionBuilder(NamedExtensionFluent fluent) {
      this(fluent, new NamedExtension());
   }

   public NamedExtensionBuilder(NamedExtensionFluent fluent, NamedExtension instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedExtensionBuilder(NamedExtension instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedExtension build() {
      NamedExtension buildable = new NamedExtension(this.fluent.getExtension(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
