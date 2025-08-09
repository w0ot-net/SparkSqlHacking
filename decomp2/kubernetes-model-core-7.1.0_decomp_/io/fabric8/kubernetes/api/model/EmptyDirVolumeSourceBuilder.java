package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EmptyDirVolumeSourceBuilder extends EmptyDirVolumeSourceFluent implements VisitableBuilder {
   EmptyDirVolumeSourceFluent fluent;

   public EmptyDirVolumeSourceBuilder() {
      this(new EmptyDirVolumeSource());
   }

   public EmptyDirVolumeSourceBuilder(EmptyDirVolumeSourceFluent fluent) {
      this(fluent, new EmptyDirVolumeSource());
   }

   public EmptyDirVolumeSourceBuilder(EmptyDirVolumeSourceFluent fluent, EmptyDirVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EmptyDirVolumeSourceBuilder(EmptyDirVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EmptyDirVolumeSource build() {
      EmptyDirVolumeSource buildable = new EmptyDirVolumeSource(this.fluent.getMedium(), this.fluent.buildSizeLimit());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
