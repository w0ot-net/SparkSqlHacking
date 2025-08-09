package io.fabric8.kubernetes.api.model.runtime;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RawExtensionBuilder extends RawExtensionFluent implements VisitableBuilder {
   RawExtensionFluent fluent;

   public RawExtensionBuilder() {
      this(new RawExtension());
   }

   public RawExtensionBuilder(RawExtensionFluent fluent) {
      this(fluent, new RawExtension());
   }

   public RawExtensionBuilder(RawExtensionFluent fluent, RawExtension instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RawExtensionBuilder(RawExtension instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RawExtension build() {
      RawExtension buildable = new RawExtension(this.fluent.getValue());
      return buildable;
   }
}
