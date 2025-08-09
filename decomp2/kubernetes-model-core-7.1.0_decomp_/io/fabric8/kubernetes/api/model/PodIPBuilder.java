package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodIPBuilder extends PodIPFluent implements VisitableBuilder {
   PodIPFluent fluent;

   public PodIPBuilder() {
      this(new PodIP());
   }

   public PodIPBuilder(PodIPFluent fluent) {
      this(fluent, new PodIP());
   }

   public PodIPBuilder(PodIPFluent fluent, PodIP instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodIPBuilder(PodIP instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodIP build() {
      PodIP buildable = new PodIP(this.fluent.getIp());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
