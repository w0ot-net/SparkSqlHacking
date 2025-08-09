package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodReadinessGateBuilder extends PodReadinessGateFluent implements VisitableBuilder {
   PodReadinessGateFluent fluent;

   public PodReadinessGateBuilder() {
      this(new PodReadinessGate());
   }

   public PodReadinessGateBuilder(PodReadinessGateFluent fluent) {
      this(fluent, new PodReadinessGate());
   }

   public PodReadinessGateBuilder(PodReadinessGateFluent fluent, PodReadinessGate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodReadinessGateBuilder(PodReadinessGate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodReadinessGate build() {
      PodReadinessGate buildable = new PodReadinessGate(this.fluent.getConditionType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
