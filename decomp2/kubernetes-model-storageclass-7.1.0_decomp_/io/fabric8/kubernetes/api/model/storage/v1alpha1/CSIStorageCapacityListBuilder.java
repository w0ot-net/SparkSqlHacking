package io.fabric8.kubernetes.api.model.storage.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIStorageCapacityListBuilder extends CSIStorageCapacityListFluent implements VisitableBuilder {
   CSIStorageCapacityListFluent fluent;

   public CSIStorageCapacityListBuilder() {
      this(new CSIStorageCapacityList());
   }

   public CSIStorageCapacityListBuilder(CSIStorageCapacityListFluent fluent) {
      this(fluent, new CSIStorageCapacityList());
   }

   public CSIStorageCapacityListBuilder(CSIStorageCapacityListFluent fluent, CSIStorageCapacityList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIStorageCapacityListBuilder(CSIStorageCapacityList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIStorageCapacityList build() {
      CSIStorageCapacityList buildable = new CSIStorageCapacityList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
