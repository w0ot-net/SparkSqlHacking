package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimStatusBuilder extends PersistentVolumeClaimStatusFluent implements VisitableBuilder {
   PersistentVolumeClaimStatusFluent fluent;

   public PersistentVolumeClaimStatusBuilder() {
      this(new PersistentVolumeClaimStatus());
   }

   public PersistentVolumeClaimStatusBuilder(PersistentVolumeClaimStatusFluent fluent) {
      this(fluent, new PersistentVolumeClaimStatus());
   }

   public PersistentVolumeClaimStatusBuilder(PersistentVolumeClaimStatusFluent fluent, PersistentVolumeClaimStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimStatusBuilder(PersistentVolumeClaimStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimStatus build() {
      PersistentVolumeClaimStatus buildable = new PersistentVolumeClaimStatus(this.fluent.getAccessModes(), this.fluent.getAllocatedResourceStatuses(), this.fluent.getAllocatedResources(), this.fluent.getCapacity(), this.fluent.buildConditions(), this.fluent.getCurrentVolumeAttributesClassName(), this.fluent.buildModifyVolumeStatus(), this.fluent.getPhase());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
