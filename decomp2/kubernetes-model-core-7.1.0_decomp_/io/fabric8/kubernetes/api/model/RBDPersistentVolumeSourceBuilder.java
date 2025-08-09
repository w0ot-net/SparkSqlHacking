package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RBDPersistentVolumeSourceBuilder extends RBDPersistentVolumeSourceFluent implements VisitableBuilder {
   RBDPersistentVolumeSourceFluent fluent;

   public RBDPersistentVolumeSourceBuilder() {
      this(new RBDPersistentVolumeSource());
   }

   public RBDPersistentVolumeSourceBuilder(RBDPersistentVolumeSourceFluent fluent) {
      this(fluent, new RBDPersistentVolumeSource());
   }

   public RBDPersistentVolumeSourceBuilder(RBDPersistentVolumeSourceFluent fluent, RBDPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RBDPersistentVolumeSourceBuilder(RBDPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RBDPersistentVolumeSource build() {
      RBDPersistentVolumeSource buildable = new RBDPersistentVolumeSource(this.fluent.getFsType(), this.fluent.getImage(), this.fluent.getKeyring(), this.fluent.getMonitors(), this.fluent.getPool(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
