package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageVersionMigrationBuilder extends StorageVersionMigrationFluent implements VisitableBuilder {
   StorageVersionMigrationFluent fluent;

   public StorageVersionMigrationBuilder() {
      this(new StorageVersionMigration());
   }

   public StorageVersionMigrationBuilder(StorageVersionMigrationFluent fluent) {
      this(fluent, new StorageVersionMigration());
   }

   public StorageVersionMigrationBuilder(StorageVersionMigrationFluent fluent, StorageVersionMigration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageVersionMigrationBuilder(StorageVersionMigration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageVersionMigration build() {
      StorageVersionMigration buildable = new StorageVersionMigration(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
