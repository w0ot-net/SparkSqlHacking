package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HostPathVolumeSourceBuilder extends HostPathVolumeSourceFluent implements VisitableBuilder {
   HostPathVolumeSourceFluent fluent;

   public HostPathVolumeSourceBuilder() {
      this(new HostPathVolumeSource());
   }

   public HostPathVolumeSourceBuilder(HostPathVolumeSourceFluent fluent) {
      this(fluent, new HostPathVolumeSource());
   }

   public HostPathVolumeSourceBuilder(HostPathVolumeSourceFluent fluent, HostPathVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HostPathVolumeSourceBuilder(HostPathVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HostPathVolumeSource build() {
      HostPathVolumeSource buildable = new HostPathVolumeSource(this.fluent.getPath(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
