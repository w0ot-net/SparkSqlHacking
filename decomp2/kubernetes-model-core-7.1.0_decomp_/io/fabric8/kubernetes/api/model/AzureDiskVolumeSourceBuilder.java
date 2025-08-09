package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AzureDiskVolumeSourceBuilder extends AzureDiskVolumeSourceFluent implements VisitableBuilder {
   AzureDiskVolumeSourceFluent fluent;

   public AzureDiskVolumeSourceBuilder() {
      this(new AzureDiskVolumeSource());
   }

   public AzureDiskVolumeSourceBuilder(AzureDiskVolumeSourceFluent fluent) {
      this(fluent, new AzureDiskVolumeSource());
   }

   public AzureDiskVolumeSourceBuilder(AzureDiskVolumeSourceFluent fluent, AzureDiskVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AzureDiskVolumeSourceBuilder(AzureDiskVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AzureDiskVolumeSource build() {
      AzureDiskVolumeSource buildable = new AzureDiskVolumeSource(this.fluent.getCachingMode(), this.fluent.getDiskName(), this.fluent.getDiskURI(), this.fluent.getFsType(), this.fluent.getKind(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
