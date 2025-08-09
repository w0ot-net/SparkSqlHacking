package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttributesClassListBuilder extends VolumeAttributesClassListFluent implements VisitableBuilder {
   VolumeAttributesClassListFluent fluent;

   public VolumeAttributesClassListBuilder() {
      this(new VolumeAttributesClassList());
   }

   public VolumeAttributesClassListBuilder(VolumeAttributesClassListFluent fluent) {
      this(fluent, new VolumeAttributesClassList());
   }

   public VolumeAttributesClassListBuilder(VolumeAttributesClassListFluent fluent, VolumeAttributesClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttributesClassListBuilder(VolumeAttributesClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttributesClassList build() {
      VolumeAttributesClassList buildable = new VolumeAttributesClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
