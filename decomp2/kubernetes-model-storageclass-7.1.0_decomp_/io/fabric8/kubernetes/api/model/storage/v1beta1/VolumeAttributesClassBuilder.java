package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttributesClassBuilder extends VolumeAttributesClassFluent implements VisitableBuilder {
   VolumeAttributesClassFluent fluent;

   public VolumeAttributesClassBuilder() {
      this(new VolumeAttributesClass());
   }

   public VolumeAttributesClassBuilder(VolumeAttributesClassFluent fluent) {
      this(fluent, new VolumeAttributesClass());
   }

   public VolumeAttributesClassBuilder(VolumeAttributesClassFluent fluent, VolumeAttributesClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttributesClassBuilder(VolumeAttributesClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttributesClass build() {
      VolumeAttributesClass buildable = new VolumeAttributesClass(this.fluent.getApiVersion(), this.fluent.getDriverName(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
