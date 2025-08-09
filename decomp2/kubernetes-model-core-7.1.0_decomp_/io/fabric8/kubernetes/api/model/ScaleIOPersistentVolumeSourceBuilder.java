package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScaleIOPersistentVolumeSourceBuilder extends ScaleIOPersistentVolumeSourceFluent implements VisitableBuilder {
   ScaleIOPersistentVolumeSourceFluent fluent;

   public ScaleIOPersistentVolumeSourceBuilder() {
      this(new ScaleIOPersistentVolumeSource());
   }

   public ScaleIOPersistentVolumeSourceBuilder(ScaleIOPersistentVolumeSourceFluent fluent) {
      this(fluent, new ScaleIOPersistentVolumeSource());
   }

   public ScaleIOPersistentVolumeSourceBuilder(ScaleIOPersistentVolumeSourceFluent fluent, ScaleIOPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScaleIOPersistentVolumeSourceBuilder(ScaleIOPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScaleIOPersistentVolumeSource build() {
      ScaleIOPersistentVolumeSource buildable = new ScaleIOPersistentVolumeSource(this.fluent.getFsType(), this.fluent.getGateway(), this.fluent.getProtectionDomain(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getSslEnabled(), this.fluent.getStorageMode(), this.fluent.getStoragePool(), this.fluent.getSystem(), this.fluent.getVolumeName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
