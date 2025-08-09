package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttachmentSpecBuilder extends VolumeAttachmentSpecFluent implements VisitableBuilder {
   VolumeAttachmentSpecFluent fluent;

   public VolumeAttachmentSpecBuilder() {
      this(new VolumeAttachmentSpec());
   }

   public VolumeAttachmentSpecBuilder(VolumeAttachmentSpecFluent fluent) {
      this(fluent, new VolumeAttachmentSpec());
   }

   public VolumeAttachmentSpecBuilder(VolumeAttachmentSpecFluent fluent, VolumeAttachmentSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttachmentSpecBuilder(VolumeAttachmentSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttachmentSpec build() {
      VolumeAttachmentSpec buildable = new VolumeAttachmentSpec(this.fluent.getAttacher(), this.fluent.getNodeName(), this.fluent.buildSource());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
