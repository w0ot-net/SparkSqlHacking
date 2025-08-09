package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RBDVolumeSourceBuilder extends RBDVolumeSourceFluent implements VisitableBuilder {
   RBDVolumeSourceFluent fluent;

   public RBDVolumeSourceBuilder() {
      this(new RBDVolumeSource());
   }

   public RBDVolumeSourceBuilder(RBDVolumeSourceFluent fluent) {
      this(fluent, new RBDVolumeSource());
   }

   public RBDVolumeSourceBuilder(RBDVolumeSourceFluent fluent, RBDVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RBDVolumeSourceBuilder(RBDVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RBDVolumeSource build() {
      RBDVolumeSource buildable = new RBDVolumeSource(this.fluent.getFsType(), this.fluent.getImage(), this.fluent.getKeyring(), this.fluent.getMonitors(), this.fluent.getPool(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
