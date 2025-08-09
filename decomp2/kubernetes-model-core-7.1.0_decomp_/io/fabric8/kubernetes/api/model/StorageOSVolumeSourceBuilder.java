package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageOSVolumeSourceBuilder extends StorageOSVolumeSourceFluent implements VisitableBuilder {
   StorageOSVolumeSourceFluent fluent;

   public StorageOSVolumeSourceBuilder() {
      this(new StorageOSVolumeSource());
   }

   public StorageOSVolumeSourceBuilder(StorageOSVolumeSourceFluent fluent) {
      this(fluent, new StorageOSVolumeSource());
   }

   public StorageOSVolumeSourceBuilder(StorageOSVolumeSourceFluent fluent, StorageOSVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageOSVolumeSourceBuilder(StorageOSVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageOSVolumeSource build() {
      StorageOSVolumeSource buildable = new StorageOSVolumeSource(this.fluent.getFsType(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getVolumeName(), this.fluent.getVolumeNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
