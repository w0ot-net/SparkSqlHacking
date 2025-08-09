package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlockerVolumeSourceBuilder extends FlockerVolumeSourceFluent implements VisitableBuilder {
   FlockerVolumeSourceFluent fluent;

   public FlockerVolumeSourceBuilder() {
      this(new FlockerVolumeSource());
   }

   public FlockerVolumeSourceBuilder(FlockerVolumeSourceFluent fluent) {
      this(fluent, new FlockerVolumeSource());
   }

   public FlockerVolumeSourceBuilder(FlockerVolumeSourceFluent fluent, FlockerVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlockerVolumeSourceBuilder(FlockerVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlockerVolumeSource build() {
      FlockerVolumeSource buildable = new FlockerVolumeSource(this.fluent.getDatasetName(), this.fluent.getDatasetUUID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
