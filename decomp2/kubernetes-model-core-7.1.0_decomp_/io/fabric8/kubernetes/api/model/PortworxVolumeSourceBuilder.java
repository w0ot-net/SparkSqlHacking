package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PortworxVolumeSourceBuilder extends PortworxVolumeSourceFluent implements VisitableBuilder {
   PortworxVolumeSourceFluent fluent;

   public PortworxVolumeSourceBuilder() {
      this(new PortworxVolumeSource());
   }

   public PortworxVolumeSourceBuilder(PortworxVolumeSourceFluent fluent) {
      this(fluent, new PortworxVolumeSource());
   }

   public PortworxVolumeSourceBuilder(PortworxVolumeSourceFluent fluent, PortworxVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PortworxVolumeSourceBuilder(PortworxVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PortworxVolumeSource build() {
      PortworxVolumeSource buildable = new PortworxVolumeSource(this.fluent.getFsType(), this.fluent.getReadOnly(), this.fluent.getVolumeID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
