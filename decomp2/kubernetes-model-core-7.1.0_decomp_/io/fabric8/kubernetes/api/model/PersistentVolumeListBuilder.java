package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeListBuilder extends PersistentVolumeListFluent implements VisitableBuilder {
   PersistentVolumeListFluent fluent;

   public PersistentVolumeListBuilder() {
      this(new PersistentVolumeList());
   }

   public PersistentVolumeListBuilder(PersistentVolumeListFluent fluent) {
      this(fluent, new PersistentVolumeList());
   }

   public PersistentVolumeListBuilder(PersistentVolumeListFluent fluent, PersistentVolumeList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeListBuilder(PersistentVolumeList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeList build() {
      PersistentVolumeList buildable = new PersistentVolumeList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
