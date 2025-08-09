package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CinderPersistentVolumeSourceBuilder extends CinderPersistentVolumeSourceFluent implements VisitableBuilder {
   CinderPersistentVolumeSourceFluent fluent;

   public CinderPersistentVolumeSourceBuilder() {
      this(new CinderPersistentVolumeSource());
   }

   public CinderPersistentVolumeSourceBuilder(CinderPersistentVolumeSourceFluent fluent) {
      this(fluent, new CinderPersistentVolumeSource());
   }

   public CinderPersistentVolumeSourceBuilder(CinderPersistentVolumeSourceFluent fluent, CinderPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CinderPersistentVolumeSourceBuilder(CinderPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CinderPersistentVolumeSource build() {
      CinderPersistentVolumeSource buildable = new CinderPersistentVolumeSource(this.fluent.getFsType(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getVolumeID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
