package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeSpecBuilder extends PersistentVolumeSpecFluent implements VisitableBuilder {
   PersistentVolumeSpecFluent fluent;

   public PersistentVolumeSpecBuilder() {
      this(new PersistentVolumeSpec());
   }

   public PersistentVolumeSpecBuilder(PersistentVolumeSpecFluent fluent) {
      this(fluent, new PersistentVolumeSpec());
   }

   public PersistentVolumeSpecBuilder(PersistentVolumeSpecFluent fluent, PersistentVolumeSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeSpecBuilder(PersistentVolumeSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeSpec build() {
      PersistentVolumeSpec buildable = new PersistentVolumeSpec(this.fluent.getAccessModes(), this.fluent.buildAwsElasticBlockStore(), this.fluent.buildAzureDisk(), this.fluent.buildAzureFile(), this.fluent.getCapacity(), this.fluent.buildCephfs(), this.fluent.buildCinder(), this.fluent.buildClaimRef(), this.fluent.buildCsi(), this.fluent.buildFc(), this.fluent.buildFlexVolume(), this.fluent.buildFlocker(), this.fluent.buildGcePersistentDisk(), this.fluent.buildGlusterfs(), this.fluent.buildHostPath(), this.fluent.buildIscsi(), this.fluent.buildLocal(), this.fluent.getMountOptions(), this.fluent.buildNfs(), this.fluent.buildNodeAffinity(), this.fluent.getPersistentVolumeReclaimPolicy(), this.fluent.buildPhotonPersistentDisk(), this.fluent.buildPortworxVolume(), this.fluent.buildQuobyte(), this.fluent.buildRbd(), this.fluent.buildScaleIO(), this.fluent.getStorageClassName(), this.fluent.buildStorageos(), this.fluent.getVolumeAttributesClassName(), this.fluent.getVolumeMode(), this.fluent.buildVsphereVolume());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
