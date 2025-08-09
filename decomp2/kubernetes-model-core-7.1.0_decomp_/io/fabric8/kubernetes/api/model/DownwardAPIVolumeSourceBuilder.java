package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DownwardAPIVolumeSourceBuilder extends DownwardAPIVolumeSourceFluent implements VisitableBuilder {
   DownwardAPIVolumeSourceFluent fluent;

   public DownwardAPIVolumeSourceBuilder() {
      this(new DownwardAPIVolumeSource());
   }

   public DownwardAPIVolumeSourceBuilder(DownwardAPIVolumeSourceFluent fluent) {
      this(fluent, new DownwardAPIVolumeSource());
   }

   public DownwardAPIVolumeSourceBuilder(DownwardAPIVolumeSourceFluent fluent, DownwardAPIVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DownwardAPIVolumeSourceBuilder(DownwardAPIVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DownwardAPIVolumeSource build() {
      DownwardAPIVolumeSource buildable = new DownwardAPIVolumeSource(this.fluent.getDefaultMode(), this.fluent.buildItems());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
