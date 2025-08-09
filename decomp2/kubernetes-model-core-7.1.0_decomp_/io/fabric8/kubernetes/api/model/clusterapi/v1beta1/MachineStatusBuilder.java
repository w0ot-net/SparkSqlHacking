package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineStatusBuilder extends MachineStatusFluent implements VisitableBuilder {
   MachineStatusFluent fluent;

   public MachineStatusBuilder() {
      this(new MachineStatus());
   }

   public MachineStatusBuilder(MachineStatusFluent fluent) {
      this(fluent, new MachineStatus());
   }

   public MachineStatusBuilder(MachineStatusFluent fluent, MachineStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineStatusBuilder(MachineStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineStatus build() {
      MachineStatus buildable = new MachineStatus(this.fluent.buildAddresses(), this.fluent.getBootstrapReady(), this.fluent.getCertificatesExpiryDate(), this.fluent.buildConditions(), this.fluent.buildDeletion(), this.fluent.getFailureMessage(), this.fluent.getFailureReason(), this.fluent.getInfrastructureReady(), this.fluent.getLastUpdated(), this.fluent.buildNodeInfo(), this.fluent.buildNodeRef(), this.fluent.getObservedGeneration(), this.fluent.getPhase(), this.fluent.buildV1beta2());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
