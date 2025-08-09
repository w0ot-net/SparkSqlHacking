package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScaleIOVolumeSourceBuilder extends ScaleIOVolumeSourceFluent implements VisitableBuilder {
   ScaleIOVolumeSourceFluent fluent;

   public ScaleIOVolumeSourceBuilder() {
      this(new ScaleIOVolumeSource());
   }

   public ScaleIOVolumeSourceBuilder(ScaleIOVolumeSourceFluent fluent) {
      this(fluent, new ScaleIOVolumeSource());
   }

   public ScaleIOVolumeSourceBuilder(ScaleIOVolumeSourceFluent fluent, ScaleIOVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScaleIOVolumeSourceBuilder(ScaleIOVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScaleIOVolumeSource build() {
      ScaleIOVolumeSource buildable = new ScaleIOVolumeSource(this.fluent.getFsType(), this.fluent.getGateway(), this.fluent.getProtectionDomain(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getSslEnabled(), this.fluent.getStorageMode(), this.fluent.getStoragePool(), this.fluent.getSystem(), this.fluent.getVolumeName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
