package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimSpecBuilder extends PersistentVolumeClaimSpecFluent implements VisitableBuilder {
   PersistentVolumeClaimSpecFluent fluent;

   public PersistentVolumeClaimSpecBuilder() {
      this(new PersistentVolumeClaimSpec());
   }

   public PersistentVolumeClaimSpecBuilder(PersistentVolumeClaimSpecFluent fluent) {
      this(fluent, new PersistentVolumeClaimSpec());
   }

   public PersistentVolumeClaimSpecBuilder(PersistentVolumeClaimSpecFluent fluent, PersistentVolumeClaimSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimSpecBuilder(PersistentVolumeClaimSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimSpec build() {
      PersistentVolumeClaimSpec buildable = new PersistentVolumeClaimSpec(this.fluent.getAccessModes(), this.fluent.buildDataSource(), this.fluent.buildDataSourceRef(), this.fluent.buildResources(), this.fluent.buildSelector(), this.fluent.getStorageClassName(), this.fluent.getVolumeAttributesClassName(), this.fluent.getVolumeMode(), this.fluent.getVolumeName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
