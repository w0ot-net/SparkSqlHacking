package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSchedulingGateBuilder extends PodSchedulingGateFluent implements VisitableBuilder {
   PodSchedulingGateFluent fluent;

   public PodSchedulingGateBuilder() {
      this(new PodSchedulingGate());
   }

   public PodSchedulingGateBuilder(PodSchedulingGateFluent fluent) {
      this(fluent, new PodSchedulingGate());
   }

   public PodSchedulingGateBuilder(PodSchedulingGateFluent fluent, PodSchedulingGate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSchedulingGateBuilder(PodSchedulingGate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSchedulingGate build() {
      PodSchedulingGate buildable = new PodSchedulingGate(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
