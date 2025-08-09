package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimVolumeSourceBuilder extends PersistentVolumeClaimVolumeSourceFluent implements VisitableBuilder {
   PersistentVolumeClaimVolumeSourceFluent fluent;

   public PersistentVolumeClaimVolumeSourceBuilder() {
      this(new PersistentVolumeClaimVolumeSource());
   }

   public PersistentVolumeClaimVolumeSourceBuilder(PersistentVolumeClaimVolumeSourceFluent fluent) {
      this(fluent, new PersistentVolumeClaimVolumeSource());
   }

   public PersistentVolumeClaimVolumeSourceBuilder(PersistentVolumeClaimVolumeSourceFluent fluent, PersistentVolumeClaimVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimVolumeSourceBuilder(PersistentVolumeClaimVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimVolumeSource build() {
      PersistentVolumeClaimVolumeSource buildable = new PersistentVolumeClaimVolumeSource(this.fluent.getClaimName(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
