package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class QuobyteVolumeSourceBuilder extends QuobyteVolumeSourceFluent implements VisitableBuilder {
   QuobyteVolumeSourceFluent fluent;

   public QuobyteVolumeSourceBuilder() {
      this(new QuobyteVolumeSource());
   }

   public QuobyteVolumeSourceBuilder(QuobyteVolumeSourceFluent fluent) {
      this(fluent, new QuobyteVolumeSource());
   }

   public QuobyteVolumeSourceBuilder(QuobyteVolumeSourceFluent fluent, QuobyteVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public QuobyteVolumeSourceBuilder(QuobyteVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public QuobyteVolumeSource build() {
      QuobyteVolumeSource buildable = new QuobyteVolumeSource(this.fluent.getGroup(), this.fluent.getReadOnly(), this.fluent.getRegistry(), this.fluent.getTenant(), this.fluent.getUser(), this.fluent.getVolume());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
