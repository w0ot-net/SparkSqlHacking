package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CephFSVolumeSourceBuilder extends CephFSVolumeSourceFluent implements VisitableBuilder {
   CephFSVolumeSourceFluent fluent;

   public CephFSVolumeSourceBuilder() {
      this(new CephFSVolumeSource());
   }

   public CephFSVolumeSourceBuilder(CephFSVolumeSourceFluent fluent) {
      this(fluent, new CephFSVolumeSource());
   }

   public CephFSVolumeSourceBuilder(CephFSVolumeSourceFluent fluent, CephFSVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CephFSVolumeSourceBuilder(CephFSVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CephFSVolumeSource build() {
      CephFSVolumeSource buildable = new CephFSVolumeSource(this.fluent.getMonitors(), this.fluent.getPath(), this.fluent.getReadOnly(), this.fluent.getSecretFile(), this.fluent.buildSecretRef(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
