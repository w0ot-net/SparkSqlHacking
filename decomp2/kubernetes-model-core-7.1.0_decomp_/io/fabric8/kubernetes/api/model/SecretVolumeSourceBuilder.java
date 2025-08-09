package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretVolumeSourceBuilder extends SecretVolumeSourceFluent implements VisitableBuilder {
   SecretVolumeSourceFluent fluent;

   public SecretVolumeSourceBuilder() {
      this(new SecretVolumeSource());
   }

   public SecretVolumeSourceBuilder(SecretVolumeSourceFluent fluent) {
      this(fluent, new SecretVolumeSource());
   }

   public SecretVolumeSourceBuilder(SecretVolumeSourceFluent fluent, SecretVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretVolumeSourceBuilder(SecretVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretVolumeSource build() {
      SecretVolumeSource buildable = new SecretVolumeSource(this.fluent.getDefaultMode(), this.fluent.buildItems(), this.fluent.getOptional(), this.fluent.getSecretName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
