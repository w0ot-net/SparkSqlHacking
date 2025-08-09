package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineSpecBuilder extends MachineSpecFluent implements VisitableBuilder {
   MachineSpecFluent fluent;

   public MachineSpecBuilder() {
      this(new MachineSpec());
   }

   public MachineSpecBuilder(MachineSpecFluent fluent) {
      this(fluent, new MachineSpec());
   }

   public MachineSpecBuilder(MachineSpecFluent fluent, MachineSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineSpecBuilder(MachineSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineSpec build() {
      MachineSpec buildable = new MachineSpec(this.fluent.buildBootstrap(), this.fluent.getClusterName(), this.fluent.getFailureDomain(), this.fluent.buildInfrastructureRef(), this.fluent.getNodeDeletionTimeout(), this.fluent.getNodeDrainTimeout(), this.fluent.getNodeVolumeDetachTimeout(), this.fluent.getProviderID(), this.fluent.buildReadinessGates(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
