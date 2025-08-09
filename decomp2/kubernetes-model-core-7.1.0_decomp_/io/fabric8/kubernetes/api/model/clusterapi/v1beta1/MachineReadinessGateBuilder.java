package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineReadinessGateBuilder extends MachineReadinessGateFluent implements VisitableBuilder {
   MachineReadinessGateFluent fluent;

   public MachineReadinessGateBuilder() {
      this(new MachineReadinessGate());
   }

   public MachineReadinessGateBuilder(MachineReadinessGateFluent fluent) {
      this(fluent, new MachineReadinessGate());
   }

   public MachineReadinessGateBuilder(MachineReadinessGateFluent fluent, MachineReadinessGate instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineReadinessGateBuilder(MachineReadinessGate instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineReadinessGate build() {
      MachineReadinessGate buildable = new MachineReadinessGate(this.fluent.getConditionType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
