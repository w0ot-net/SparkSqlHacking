package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ISCSIPersistentVolumeSourceBuilder extends ISCSIPersistentVolumeSourceFluent implements VisitableBuilder {
   ISCSIPersistentVolumeSourceFluent fluent;

   public ISCSIPersistentVolumeSourceBuilder() {
      this(new ISCSIPersistentVolumeSource());
   }

   public ISCSIPersistentVolumeSourceBuilder(ISCSIPersistentVolumeSourceFluent fluent) {
      this(fluent, new ISCSIPersistentVolumeSource());
   }

   public ISCSIPersistentVolumeSourceBuilder(ISCSIPersistentVolumeSourceFluent fluent, ISCSIPersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ISCSIPersistentVolumeSourceBuilder(ISCSIPersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ISCSIPersistentVolumeSource build() {
      ISCSIPersistentVolumeSource buildable = new ISCSIPersistentVolumeSource(this.fluent.getChapAuthDiscovery(), this.fluent.getChapAuthSession(), this.fluent.getFsType(), this.fluent.getInitiatorName(), this.fluent.getIqn(), this.fluent.getIscsiInterface(), this.fluent.getLun(), this.fluent.getPortals(), this.fluent.getReadOnly(), this.fluent.buildSecretRef(), this.fluent.getTargetPortal());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
