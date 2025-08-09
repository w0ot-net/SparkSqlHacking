package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIPersistentVolumeSourceBuilder extends CSIPersistentVolumeSourceFluent implements VisitableBuilder {
   CSIPersistentVolumeSourceFluent fluent;

   public CSIPersistentVolumeSourceBuilder() {
      this(new CSIPersistentVolumeSource());
   }

   public CSIPersistentVolumeSourceBuilder(CSIPersistentVolumeSourceFluent fluent) {
      this(fluent, new CSIPersistentVolumeSource());
   }

   public CSIPersistentVolumeSourceBuilder(CSIPersistentVolumeSourceFluent fluent, CSIPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIPersistentVolumeSourceBuilder(CSIPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIPersistentVolumeSource build() {
      CSIPersistentVolumeSource buildable = new CSIPersistentVolumeSource(this.fluent.buildControllerExpandSecretRef(), this.fluent.buildControllerPublishSecretRef(), this.fluent.getDriver(), this.fluent.getFsType(), this.fluent.buildNodeExpandSecretRef(), this.fluent.buildNodePublishSecretRef(), this.fluent.buildNodeStageSecretRef(), this.fluent.getReadOnly(), this.fluent.getVolumeAttributes(), this.fluent.getVolumeHandle());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
