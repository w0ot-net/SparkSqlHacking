package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EphemeralVolumeSourceBuilder extends EphemeralVolumeSourceFluent implements VisitableBuilder {
   EphemeralVolumeSourceFluent fluent;

   public EphemeralVolumeSourceBuilder() {
      this(new EphemeralVolumeSource());
   }

   public EphemeralVolumeSourceBuilder(EphemeralVolumeSourceFluent fluent) {
      this(fluent, new EphemeralVolumeSource());
   }

   public EphemeralVolumeSourceBuilder(EphemeralVolumeSourceFluent fluent, EphemeralVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EphemeralVolumeSourceBuilder(EphemeralVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EphemeralVolumeSource build() {
      EphemeralVolumeSource buildable = new EphemeralVolumeSource(this.fluent.buildVolumeClaimTemplate());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
