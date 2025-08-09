package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GCEPersistentDiskVolumeSourceBuilder extends GCEPersistentDiskVolumeSourceFluent implements VisitableBuilder {
   GCEPersistentDiskVolumeSourceFluent fluent;

   public GCEPersistentDiskVolumeSourceBuilder() {
      this(new GCEPersistentDiskVolumeSource());
   }

   public GCEPersistentDiskVolumeSourceBuilder(GCEPersistentDiskVolumeSourceFluent fluent) {
      this(fluent, new GCEPersistentDiskVolumeSource());
   }

   public GCEPersistentDiskVolumeSourceBuilder(GCEPersistentDiskVolumeSourceFluent fluent, GCEPersistentDiskVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GCEPersistentDiskVolumeSourceBuilder(GCEPersistentDiskVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GCEPersistentDiskVolumeSource build() {
      GCEPersistentDiskVolumeSource buildable = new GCEPersistentDiskVolumeSource(this.fluent.getFsType(), this.fluent.getPartition(), this.fluent.getPdName(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
