package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageVersionMigrationSpecBuilder extends StorageVersionMigrationSpecFluent implements VisitableBuilder {
   StorageVersionMigrationSpecFluent fluent;

   public StorageVersionMigrationSpecBuilder() {
      this(new StorageVersionMigrationSpec());
   }

   public StorageVersionMigrationSpecBuilder(StorageVersionMigrationSpecFluent fluent) {
      this(fluent, new StorageVersionMigrationSpec());
   }

   public StorageVersionMigrationSpecBuilder(StorageVersionMigrationSpecFluent fluent, StorageVersionMigrationSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageVersionMigrationSpecBuilder(StorageVersionMigrationSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageVersionMigrationSpec build() {
      StorageVersionMigrationSpec buildable = new StorageVersionMigrationSpec(this.fluent.getContinueToken(), this.fluent.buildResource());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
