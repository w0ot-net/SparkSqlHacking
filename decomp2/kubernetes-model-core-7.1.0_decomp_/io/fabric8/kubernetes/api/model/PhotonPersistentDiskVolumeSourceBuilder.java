package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PhotonPersistentDiskVolumeSourceBuilder extends PhotonPersistentDiskVolumeSourceFluent implements VisitableBuilder {
   PhotonPersistentDiskVolumeSourceFluent fluent;

   public PhotonPersistentDiskVolumeSourceBuilder() {
      this(new PhotonPersistentDiskVolumeSource());
   }

   public PhotonPersistentDiskVolumeSourceBuilder(PhotonPersistentDiskVolumeSourceFluent fluent) {
      this(fluent, new PhotonPersistentDiskVolumeSource());
   }

   public PhotonPersistentDiskVolumeSourceBuilder(PhotonPersistentDiskVolumeSourceFluent fluent, PhotonPersistentDiskVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PhotonPersistentDiskVolumeSourceBuilder(PhotonPersistentDiskVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PhotonPersistentDiskVolumeSource build() {
      PhotonPersistentDiskVolumeSource buildable = new PhotonPersistentDiskVolumeSource(this.fluent.getFsType(), this.fluent.getPdID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
