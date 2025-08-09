package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CapabilitiesBuilder extends CapabilitiesFluent implements VisitableBuilder {
   CapabilitiesFluent fluent;

   public CapabilitiesBuilder() {
      this(new Capabilities());
   }

   public CapabilitiesBuilder(CapabilitiesFluent fluent) {
      this(fluent, new Capabilities());
   }

   public CapabilitiesBuilder(CapabilitiesFluent fluent, Capabilities instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CapabilitiesBuilder(Capabilities instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Capabilities build() {
      Capabilities buildable = new Capabilities(this.fluent.getAdd(), this.fluent.getDrop());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
