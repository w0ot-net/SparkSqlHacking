package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageVersionMigrationListBuilder extends StorageVersionMigrationListFluent implements VisitableBuilder {
   StorageVersionMigrationListFluent fluent;

   public StorageVersionMigrationListBuilder() {
      this(new StorageVersionMigrationList());
   }

   public StorageVersionMigrationListBuilder(StorageVersionMigrationListFluent fluent) {
      this(fluent, new StorageVersionMigrationList());
   }

   public StorageVersionMigrationListBuilder(StorageVersionMigrationListFluent fluent, StorageVersionMigrationList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageVersionMigrationListBuilder(StorageVersionMigrationList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageVersionMigrationList build() {
      StorageVersionMigrationList buildable = new StorageVersionMigrationList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
