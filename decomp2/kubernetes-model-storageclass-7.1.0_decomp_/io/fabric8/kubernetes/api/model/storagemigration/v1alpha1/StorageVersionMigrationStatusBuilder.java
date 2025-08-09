package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageVersionMigrationStatusBuilder extends StorageVersionMigrationStatusFluent implements VisitableBuilder {
   StorageVersionMigrationStatusFluent fluent;

   public StorageVersionMigrationStatusBuilder() {
      this(new StorageVersionMigrationStatus());
   }

   public StorageVersionMigrationStatusBuilder(StorageVersionMigrationStatusFluent fluent) {
      this(fluent, new StorageVersionMigrationStatus());
   }

   public StorageVersionMigrationStatusBuilder(StorageVersionMigrationStatusFluent fluent, StorageVersionMigrationStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageVersionMigrationStatusBuilder(StorageVersionMigrationStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageVersionMigrationStatus build() {
      StorageVersionMigrationStatus buildable = new StorageVersionMigrationStatus(this.fluent.buildConditions(), this.fluent.getResourceVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
