package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeBuilder extends PersistentVolumeFluent implements VisitableBuilder {
   PersistentVolumeFluent fluent;

   public PersistentVolumeBuilder() {
      this(new PersistentVolume());
   }

   public PersistentVolumeBuilder(PersistentVolumeFluent fluent) {
      this(fluent, new PersistentVolume());
   }

   public PersistentVolumeBuilder(PersistentVolumeFluent fluent, PersistentVolume instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeBuilder(PersistentVolume instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolume build() {
      PersistentVolume buildable = new PersistentVolume(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
