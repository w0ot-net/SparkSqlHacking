package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StorageClassListBuilder extends StorageClassListFluent implements VisitableBuilder {
   StorageClassListFluent fluent;

   public StorageClassListBuilder() {
      this(new StorageClassList());
   }

   public StorageClassListBuilder(StorageClassListFluent fluent) {
      this(fluent, new StorageClassList());
   }

   public StorageClassListBuilder(StorageClassListFluent fluent, StorageClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StorageClassListBuilder(StorageClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StorageClassList build() {
      StorageClassList buildable = new StorageClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
