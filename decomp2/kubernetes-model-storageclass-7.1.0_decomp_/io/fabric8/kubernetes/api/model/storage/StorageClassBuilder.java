package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageClassBuilder extends StorageClassFluent implements VisitableBuilder {
   StorageClassFluent fluent;

   public StorageClassBuilder() {
      this(new StorageClass());
   }

   public StorageClassBuilder(StorageClassFluent fluent) {
      this(fluent, new StorageClass());
   }

   public StorageClassBuilder(StorageClassFluent fluent, StorageClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageClassBuilder(StorageClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageClass build() {
      StorageClass buildable = new StorageClass(this.fluent.getAllowVolumeExpansion(), this.fluent.getAllowedTopologies(), this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getMountOptions(), this.fluent.getParameters(), this.fluent.getProvisioner(), this.fluent.getReclaimPolicy(), this.fluent.getVolumeBindingMode());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
