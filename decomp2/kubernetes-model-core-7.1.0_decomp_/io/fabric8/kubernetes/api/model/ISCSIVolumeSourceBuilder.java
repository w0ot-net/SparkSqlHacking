package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ISCSIVolumeSourceBuilder extends ISCSIVolumeSourceFluent implements VisitableBuilder {
   ISCSIVolumeSourceFluent fluent;

   public ISCSIVolumeSourceBuilder() {
      this(new ISCSIVolumeSource());
   }

   public ISCSIVolumeSourceBuilder(ISCSIVolumeSourceFluent fluent) {
      this(fluent, new ISCSIVolumeSource());
   }

   public ISCSIVolumeSourceBuilder(ISCSIVolumeSourceFluent fluent, ISCSIVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ISCSIVolumeSourceBuilder(ISCSIVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ISCSIVolumeSource build() {
      ISCSIVolumeSource buildable = new ISCSIVolumeSource(this.fluent.getChapAuthDiscovery(), this.fluent.getChapAuthSession(), this.fluent.getFsType(), this.fluent.getInitiatorName(), this.fluent.getIqn(), this.fluent.getIscsiInterface(), this.fluent.getLun(), this.fluent.getPortals(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getTargetPortal());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
