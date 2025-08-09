package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeStatusBuilder extends PersistentVolumeStatusFluent implements VisitableBuilder {
   PersistentVolumeStatusFluent fluent;

   public PersistentVolumeStatusBuilder() {
      this(new PersistentVolumeStatus());
   }

   public PersistentVolumeStatusBuilder(PersistentVolumeStatusFluent fluent) {
      this(fluent, new PersistentVolumeStatus());
   }

   public PersistentVolumeStatusBuilder(PersistentVolumeStatusFluent fluent, PersistentVolumeStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeStatusBuilder(PersistentVolumeStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeStatus build() {
      PersistentVolumeStatus buildable = new PersistentVolumeStatus(this.fluent.getLastPhaseTransitionTime(), this.fluent.getMessage(), this.fluent.getPhase(), this.fluent.getReason());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
