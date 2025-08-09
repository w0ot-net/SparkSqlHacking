package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GlusterfsPersistentVolumeSourceBuilder extends GlusterfsPersistentVolumeSourceFluent implements VisitableBuilder {
   GlusterfsPersistentVolumeSourceFluent fluent;

   public GlusterfsPersistentVolumeSourceBuilder() {
      this(new GlusterfsPersistentVolumeSource());
   }

   public GlusterfsPersistentVolumeSourceBuilder(GlusterfsPersistentVolumeSourceFluent fluent) {
      this(fluent, new GlusterfsPersistentVolumeSource());
   }

   public GlusterfsPersistentVolumeSourceBuilder(GlusterfsPersistentVolumeSourceFluent fluent, GlusterfsPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GlusterfsPersistentVolumeSourceBuilder(GlusterfsPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GlusterfsPersistentVolumeSource build() {
      GlusterfsPersistentVolumeSource buildable = new GlusterfsPersistentVolumeSource(this.fluent.getEndpoints(), this.fluent.getEndpointsNamespace(), this.fluent.getPath(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
