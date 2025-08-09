package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CinderVolumeSourceBuilder extends CinderVolumeSourceFluent implements VisitableBuilder {
   CinderVolumeSourceFluent fluent;

   public CinderVolumeSourceBuilder() {
      this(new CinderVolumeSource());
   }

   public CinderVolumeSourceBuilder(CinderVolumeSourceFluent fluent) {
      this(fluent, new CinderVolumeSource());
   }

   public CinderVolumeSourceBuilder(CinderVolumeSourceFluent fluent, CinderVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CinderVolumeSourceBuilder(CinderVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CinderVolumeSource build() {
      CinderVolumeSource buildable = new CinderVolumeSource(this.fluent.getFsType(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getVolumeID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
