package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ModifyVolumeStatusBuilder extends ModifyVolumeStatusFluent implements VisitableBuilder {
   ModifyVolumeStatusFluent fluent;

   public ModifyVolumeStatusBuilder() {
      this(new ModifyVolumeStatus());
   }

   public ModifyVolumeStatusBuilder(ModifyVolumeStatusFluent fluent) {
      this(fluent, new ModifyVolumeStatus());
   }

   public ModifyVolumeStatusBuilder(ModifyVolumeStatusFluent fluent, ModifyVolumeStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ModifyVolumeStatusBuilder(ModifyVolumeStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ModifyVolumeStatus build() {
      ModifyVolumeStatus buildable = new ModifyVolumeStatus(this.fluent.getStatus(), this.fluent.getTargetVolumeAttributesClassName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
