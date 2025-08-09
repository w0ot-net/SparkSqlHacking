package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FCVolumeSourceBuilder extends FCVolumeSourceFluent implements VisitableBuilder {
   FCVolumeSourceFluent fluent;

   public FCVolumeSourceBuilder() {
      this(new FCVolumeSource());
   }

   public FCVolumeSourceBuilder(FCVolumeSourceFluent fluent) {
      this(fluent, new FCVolumeSource());
   }

   public FCVolumeSourceBuilder(FCVolumeSourceFluent fluent, FCVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FCVolumeSourceBuilder(FCVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FCVolumeSource build() {
      FCVolumeSource buildable = new FCVolumeSource(this.fluent.getFsType(), this.fluent.getLun(), this.fluent.getReadOnly(), this.fluent.getTargetWWNs(), this.fluent.getWwids());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
