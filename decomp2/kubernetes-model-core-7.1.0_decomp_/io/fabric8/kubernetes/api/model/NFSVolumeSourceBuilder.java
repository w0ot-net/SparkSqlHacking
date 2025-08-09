package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NFSVolumeSourceBuilder extends NFSVolumeSourceFluent implements VisitableBuilder {
   NFSVolumeSourceFluent fluent;

   public NFSVolumeSourceBuilder() {
      this(new NFSVolumeSource());
   }

   public NFSVolumeSourceBuilder(NFSVolumeSourceFluent fluent) {
      this(fluent, new NFSVolumeSource());
   }

   public NFSVolumeSourceBuilder(NFSVolumeSourceFluent fluent, NFSVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NFSVolumeSourceBuilder(NFSVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NFSVolumeSource build() {
      NFSVolumeSource buildable = new NFSVolumeSource(this.fluent.getPath(), this.fluent.getReadOnly(), this.fluent.getServer());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
