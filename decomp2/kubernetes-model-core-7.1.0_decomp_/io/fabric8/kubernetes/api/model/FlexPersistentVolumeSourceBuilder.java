package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlexPersistentVolumeSourceBuilder extends FlexPersistentVolumeSourceFluent implements VisitableBuilder {
   FlexPersistentVolumeSourceFluent fluent;

   public FlexPersistentVolumeSourceBuilder() {
      this(new FlexPersistentVolumeSource());
   }

   public FlexPersistentVolumeSourceBuilder(FlexPersistentVolumeSourceFluent fluent) {
      this(fluent, new FlexPersistentVolumeSource());
   }

   public FlexPersistentVolumeSourceBuilder(FlexPersistentVolumeSourceFluent fluent, FlexPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlexPersistentVolumeSourceBuilder(FlexPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlexPersistentVolumeSource build() {
      FlexPersistentVolumeSource buildable = new FlexPersistentVolumeSource(this.fluent.getDriver(), this.fluent.getFsType(), this.fluent.getOptions(), this.fluent.getReadOnly(), this.fluent.buildSecretRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
