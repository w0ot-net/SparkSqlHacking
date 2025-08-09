package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeNodeAffinityBuilder extends VolumeNodeAffinityFluent implements VisitableBuilder {
   VolumeNodeAffinityFluent fluent;

   public VolumeNodeAffinityBuilder() {
      this(new VolumeNodeAffinity());
   }

   public VolumeNodeAffinityBuilder(VolumeNodeAffinityFluent fluent) {
      this(fluent, new VolumeNodeAffinity());
   }

   public VolumeNodeAffinityBuilder(VolumeNodeAffinityFluent fluent, VolumeNodeAffinity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeNodeAffinityBuilder(VolumeNodeAffinity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeNodeAffinity build() {
      VolumeNodeAffinity buildable = new VolumeNodeAffinity(this.fluent.buildRequired());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
