package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodOSBuilder extends PodOSFluent implements VisitableBuilder {
   PodOSFluent fluent;

   public PodOSBuilder() {
      this(new PodOS());
   }

   public PodOSBuilder(PodOSFluent fluent) {
      this(fluent, new PodOS());
   }

   public PodOSBuilder(PodOSFluent fluent, PodOS instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodOSBuilder(PodOS instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodOS build() {
      PodOS buildable = new PodOS(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
