package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GlusterfsVolumeSourceBuilder extends GlusterfsVolumeSourceFluent implements VisitableBuilder {
   GlusterfsVolumeSourceFluent fluent;

   public GlusterfsVolumeSourceBuilder() {
      this(new GlusterfsVolumeSource());
   }

   public GlusterfsVolumeSourceBuilder(GlusterfsVolumeSourceFluent fluent) {
      this(fluent, new GlusterfsVolumeSource());
   }

   public GlusterfsVolumeSourceBuilder(GlusterfsVolumeSourceFluent fluent, GlusterfsVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GlusterfsVolumeSourceBuilder(GlusterfsVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GlusterfsVolumeSource build() {
      GlusterfsVolumeSource buildable = new GlusterfsVolumeSource(this.fluent.getEndpoints(), this.fluent.getPath(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
