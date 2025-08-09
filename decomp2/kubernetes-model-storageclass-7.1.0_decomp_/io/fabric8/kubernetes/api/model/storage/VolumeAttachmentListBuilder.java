package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttachmentListBuilder extends VolumeAttachmentListFluent implements VisitableBuilder {
   VolumeAttachmentListFluent fluent;

   public VolumeAttachmentListBuilder() {
      this(new VolumeAttachmentList());
   }

   public VolumeAttachmentListBuilder(VolumeAttachmentListFluent fluent) {
      this(fluent, new VolumeAttachmentList());
   }

   public VolumeAttachmentListBuilder(VolumeAttachmentListFluent fluent, VolumeAttachmentList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttachmentListBuilder(VolumeAttachmentList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttachmentList build() {
      VolumeAttachmentList buildable = new VolumeAttachmentList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
