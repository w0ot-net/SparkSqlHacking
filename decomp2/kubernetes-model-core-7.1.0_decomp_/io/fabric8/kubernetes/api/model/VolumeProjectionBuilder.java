package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeProjectionBuilder extends VolumeProjectionFluent implements VisitableBuilder {
   VolumeProjectionFluent fluent;

   public VolumeProjectionBuilder() {
      this(new VolumeProjection());
   }

   public VolumeProjectionBuilder(VolumeProjectionFluent fluent) {
      this(fluent, new VolumeProjection());
   }

   public VolumeProjectionBuilder(VolumeProjectionFluent fluent, VolumeProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeProjectionBuilder(VolumeProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeProjection build() {
      VolumeProjection buildable = new VolumeProjection(this.fluent.buildClusterTrustBundle(), this.fluent.buildConfigMap(), this.fluent.buildDownwardAPI(), this.fluent.buildSecret(), this.fluent.buildServiceAccountToken());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
