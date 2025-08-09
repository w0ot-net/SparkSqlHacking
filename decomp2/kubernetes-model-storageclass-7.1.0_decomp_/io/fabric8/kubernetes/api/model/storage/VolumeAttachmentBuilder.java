package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttachmentBuilder extends VolumeAttachmentFluent implements VisitableBuilder {
   VolumeAttachmentFluent fluent;

   public VolumeAttachmentBuilder() {
      this(new VolumeAttachment());
   }

   public VolumeAttachmentBuilder(VolumeAttachmentFluent fluent) {
      this(fluent, new VolumeAttachment());
   }

   public VolumeAttachmentBuilder(VolumeAttachmentFluent fluent, VolumeAttachment instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttachmentBuilder(VolumeAttachment instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttachment build() {
      VolumeAttachment buildable = new VolumeAttachment(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
