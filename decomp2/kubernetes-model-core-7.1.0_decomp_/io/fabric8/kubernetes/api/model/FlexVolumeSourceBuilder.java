package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlexVolumeSourceBuilder extends FlexVolumeSourceFluent implements VisitableBuilder {
   FlexVolumeSourceFluent fluent;

   public FlexVolumeSourceBuilder() {
      this(new FlexVolumeSource());
   }

   public FlexVolumeSourceBuilder(FlexVolumeSourceFluent fluent) {
      this(fluent, new FlexVolumeSource());
   }

   public FlexVolumeSourceBuilder(FlexVolumeSourceFluent fluent, FlexVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlexVolumeSourceBuilder(FlexVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlexVolumeSource build() {
      FlexVolumeSource buildable = new FlexVolumeSource(this.fluent.getDriver(), this.fluent.getFsType(), this.fluent.getOptions(), this.fluent.getReadOnly(), this.fluent.buildSecretRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
