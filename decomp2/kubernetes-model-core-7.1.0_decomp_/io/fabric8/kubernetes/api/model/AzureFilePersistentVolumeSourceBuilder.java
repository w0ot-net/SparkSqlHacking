package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AzureFilePersistentVolumeSourceBuilder extends AzureFilePersistentVolumeSourceFluent implements VisitableBuilder {
   AzureFilePersistentVolumeSourceFluent fluent;

   public AzureFilePersistentVolumeSourceBuilder() {
      this(new AzureFilePersistentVolumeSource());
   }

   public AzureFilePersistentVolumeSourceBuilder(AzureFilePersistentVolumeSourceFluent fluent) {
      this(fluent, new AzureFilePersistentVolumeSource());
   }

   public AzureFilePersistentVolumeSourceBuilder(AzureFilePersistentVolumeSourceFluent fluent, AzureFilePersistentVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AzureFilePersistentVolumeSourceBuilder(AzureFilePersistentVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AzureFilePersistentVolumeSource build() {
      AzureFilePersistentVolumeSource buildable = new AzureFilePersistentVolumeSource(this.fluent.getReadOnly(), this.fluent.getSecretName(), this.fluent.getSecretNamespace(), this.fluent.getShareName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
