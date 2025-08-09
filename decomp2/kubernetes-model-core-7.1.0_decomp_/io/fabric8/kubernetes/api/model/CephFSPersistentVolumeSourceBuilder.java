package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CephFSPersistentVolumeSourceBuilder extends CephFSPersistentVolumeSourceFluent implements VisitableBuilder {
   CephFSPersistentVolumeSourceFluent fluent;

   public CephFSPersistentVolumeSourceBuilder() {
      this(new CephFSPersistentVolumeSource());
   }

   public CephFSPersistentVolumeSourceBuilder(CephFSPersistentVolumeSourceFluent fluent) {
      this(fluent, new CephFSPersistentVolumeSource());
   }

   public CephFSPersistentVolumeSourceBuilder(CephFSPersistentVolumeSourceFluent fluent, CephFSPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CephFSPersistentVolumeSourceBuilder(CephFSPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CephFSPersistentVolumeSource build() {
      CephFSPersistentVolumeSource buildable = new CephFSPersistentVolumeSource(this.fluent.getMonitors(), this.fluent.getPath(), this.fluent.getReadOnly(), this.fluent.getSecretFile(), this.fluent.buildSecretRef(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
