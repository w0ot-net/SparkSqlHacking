package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllowedFlexVolumeBuilder extends AllowedFlexVolumeFluent implements VisitableBuilder {
   AllowedFlexVolumeFluent fluent;

   public AllowedFlexVolumeBuilder() {
      this(new AllowedFlexVolume());
   }

   public AllowedFlexVolumeBuilder(AllowedFlexVolumeFluent fluent) {
      this(fluent, new AllowedFlexVolume());
   }

   public AllowedFlexVolumeBuilder(AllowedFlexVolumeFluent fluent, AllowedFlexVolume instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllowedFlexVolumeBuilder(AllowedFlexVolume instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllowedFlexVolume build() {
      AllowedFlexVolume buildable = new AllowedFlexVolume(this.fluent.getDriver());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
