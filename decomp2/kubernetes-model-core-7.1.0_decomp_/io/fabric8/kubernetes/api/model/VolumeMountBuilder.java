package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeMountBuilder extends VolumeMountFluent implements VisitableBuilder {
   VolumeMountFluent fluent;

   public VolumeMountBuilder() {
      this(new VolumeMount());
   }

   public VolumeMountBuilder(VolumeMountFluent fluent) {
      this(fluent, new VolumeMount());
   }

   public VolumeMountBuilder(VolumeMountFluent fluent, VolumeMount instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeMountBuilder(VolumeMount instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeMount build() {
      VolumeMount buildable = new VolumeMount(this.fluent.getMountPath(), this.fluent.getMountPropagation(), this.fluent.getName(), this.fluent.getReadOnly(), this.fluent.getRecursiveReadOnly(), this.fluent.getSubPath(), this.fluent.getSubPathExpr());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
