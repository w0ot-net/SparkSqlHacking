package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimListBuilder extends PersistentVolumeClaimListFluent implements VisitableBuilder {
   PersistentVolumeClaimListFluent fluent;

   public PersistentVolumeClaimListBuilder() {
      this(new PersistentVolumeClaimList());
   }

   public PersistentVolumeClaimListBuilder(PersistentVolumeClaimListFluent fluent) {
      this(fluent, new PersistentVolumeClaimList());
   }

   public PersistentVolumeClaimListBuilder(PersistentVolumeClaimListFluent fluent, PersistentVolumeClaimList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimListBuilder(PersistentVolumeClaimList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimList build() {
      PersistentVolumeClaimList buildable = new PersistentVolumeClaimList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
