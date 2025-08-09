package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimBuilder extends PersistentVolumeClaimFluent implements VisitableBuilder {
   PersistentVolumeClaimFluent fluent;

   public PersistentVolumeClaimBuilder() {
      this(new PersistentVolumeClaim());
   }

   public PersistentVolumeClaimBuilder(PersistentVolumeClaimFluent fluent) {
      this(fluent, new PersistentVolumeClaim());
   }

   public PersistentVolumeClaimBuilder(PersistentVolumeClaimFluent fluent, PersistentVolumeClaim instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimBuilder(PersistentVolumeClaim instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaim build() {
      PersistentVolumeClaim buildable = new PersistentVolumeClaim(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
