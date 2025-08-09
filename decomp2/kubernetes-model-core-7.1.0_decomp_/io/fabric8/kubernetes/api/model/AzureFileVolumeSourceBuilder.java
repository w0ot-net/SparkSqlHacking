package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AzureFileVolumeSourceBuilder extends AzureFileVolumeSourceFluent implements VisitableBuilder {
   AzureFileVolumeSourceFluent fluent;

   public AzureFileVolumeSourceBuilder() {
      this(new AzureFileVolumeSource());
   }

   public AzureFileVolumeSourceBuilder(AzureFileVolumeSourceFluent fluent) {
      this(fluent, new AzureFileVolumeSource());
   }

   public AzureFileVolumeSourceBuilder(AzureFileVolumeSourceFluent fluent, AzureFileVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AzureFileVolumeSourceBuilder(AzureFileVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AzureFileVolumeSource build() {
      AzureFileVolumeSource buildable = new AzureFileVolumeSource(this.fluent.getReadOnly(), this.fluent.getSecretName(), this.fluent.getShareName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
