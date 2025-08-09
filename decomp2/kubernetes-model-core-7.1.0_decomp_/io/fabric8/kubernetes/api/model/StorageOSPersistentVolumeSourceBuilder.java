package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageOSPersistentVolumeSourceBuilder extends StorageOSPersistentVolumeSourceFluent implements VisitableBuilder {
   StorageOSPersistentVolumeSourceFluent fluent;

   public StorageOSPersistentVolumeSourceBuilder() {
      this(new StorageOSPersistentVolumeSource());
   }

   public StorageOSPersistentVolumeSourceBuilder(StorageOSPersistentVolumeSourceFluent fluent) {
      this(fluent, new StorageOSPersistentVolumeSource());
   }

   public StorageOSPersistentVolumeSourceBuilder(StorageOSPersistentVolumeSourceFluent fluent, StorageOSPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageOSPersistentVolumeSourceBuilder(StorageOSPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageOSPersistentVolumeSource build() {
      StorageOSPersistentVolumeSource buildable = new StorageOSPersistentVolumeSource(this.fluent.getFsType(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getVolumeName(), this.fluent.getVolumeNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
