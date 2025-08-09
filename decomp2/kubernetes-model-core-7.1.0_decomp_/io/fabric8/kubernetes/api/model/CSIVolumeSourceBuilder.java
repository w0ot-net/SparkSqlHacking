package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIVolumeSourceBuilder extends CSIVolumeSourceFluent implements VisitableBuilder {
   CSIVolumeSourceFluent fluent;

   public CSIVolumeSourceBuilder() {
      this(new CSIVolumeSource());
   }

   public CSIVolumeSourceBuilder(CSIVolumeSourceFluent fluent) {
      this(fluent, new CSIVolumeSource());
   }

   public CSIVolumeSourceBuilder(CSIVolumeSourceFluent fluent, CSIVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIVolumeSourceBuilder(CSIVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIVolumeSource build() {
      CSIVolumeSource buildable = new CSIVolumeSource(this.fluent.getDriver(), this.fluent.getFsType(), this.fluent.buildNodePublishSecretRef(), this.fluent.getReadOnly(), this.fluent.getVolumeAttributes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
