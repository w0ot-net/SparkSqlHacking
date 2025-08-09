package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeMountStatusBuilder extends VolumeMountStatusFluent implements VisitableBuilder {
   VolumeMountStatusFluent fluent;

   public VolumeMountStatusBuilder() {
      this(new VolumeMountStatus());
   }

   public VolumeMountStatusBuilder(VolumeMountStatusFluent fluent) {
      this(fluent, new VolumeMountStatus());
   }

   public VolumeMountStatusBuilder(VolumeMountStatusFluent fluent, VolumeMountStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeMountStatusBuilder(VolumeMountStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeMountStatus build() {
      VolumeMountStatus buildable = new VolumeMountStatus(this.fluent.getMountPath(), this.fluent.getName(), this.fluent.getReadOnly(), this.fluent.getRecursiveReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
