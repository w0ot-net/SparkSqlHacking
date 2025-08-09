package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeBuilder extends VolumeFluent implements VisitableBuilder {
   VolumeFluent fluent;

   public VolumeBuilder() {
      this(new Volume());
   }

   public VolumeBuilder(VolumeFluent fluent) {
      this(fluent, new Volume());
   }

   public VolumeBuilder(VolumeFluent fluent, Volume instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeBuilder(Volume instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Volume build() {
      Volume buildable = new Volume(this.fluent.buildAwsElasticBlockStore(), this.fluent.buildAzureDisk(), this.fluent.buildAzureFile(), this.fluent.buildCephfs(), this.fluent.buildCinder(), this.fluent.buildConfigMap(), this.fluent.buildCsi(), this.fluent.buildDownwardAPI(), this.fluent.buildEmptyDir(), this.fluent.buildEphemeral(), this.fluent.buildFc(), this.fluent.buildFlexVolume(), this.fluent.buildFlocker(), this.fluent.buildGcePersistentDisk(), this.fluent.buildGitRepo(), this.fluent.buildGlusterfs(), this.fluent.buildHostPath(), this.fluent.buildImage(), this.fluent.buildIscsi(), this.fluent.getName(), this.fluent.buildNfs(), this.fluent.buildPersistentVolumeClaim(), this.fluent.buildPhotonPersistentDisk(), this.fluent.buildPortworxVolume(), this.fluent.buildProjected(), this.fluent.buildQuobyte(), this.fluent.buildRbd(), this.fluent.buildScaleIO(), this.fluent.buildSecret(), this.fluent.buildStorageos(), this.fluent.buildVsphereVolume());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
