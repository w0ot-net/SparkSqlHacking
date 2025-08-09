package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LocalVolumeSourceBuilder extends LocalVolumeSourceFluent implements VisitableBuilder {
   LocalVolumeSourceFluent fluent;

   public LocalVolumeSourceBuilder() {
      this(new LocalVolumeSource());
   }

   public LocalVolumeSourceBuilder(LocalVolumeSourceFluent fluent) {
      this(fluent, new LocalVolumeSource());
   }

   public LocalVolumeSourceBuilder(LocalVolumeSourceFluent fluent, LocalVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LocalVolumeSourceBuilder(LocalVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LocalVolumeSource build() {
      LocalVolumeSource buildable = new LocalVolumeSource(this.fluent.getFsType(), this.fluent.getPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
