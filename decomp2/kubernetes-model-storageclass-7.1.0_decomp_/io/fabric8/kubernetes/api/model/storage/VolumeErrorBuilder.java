package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeErrorBuilder extends VolumeErrorFluent implements VisitableBuilder {
   VolumeErrorFluent fluent;

   public VolumeErrorBuilder() {
      this(new VolumeError());
   }

   public VolumeErrorBuilder(VolumeErrorFluent fluent) {
      this(fluent, new VolumeError());
   }

   public VolumeErrorBuilder(VolumeErrorFluent fluent, VolumeError instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeErrorBuilder(VolumeError instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeError build() {
      VolumeError buildable = new VolumeError(this.fluent.getMessage(), this.fluent.getTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
