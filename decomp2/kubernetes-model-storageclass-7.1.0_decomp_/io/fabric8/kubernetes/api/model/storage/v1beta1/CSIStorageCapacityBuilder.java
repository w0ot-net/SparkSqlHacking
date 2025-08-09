package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIStorageCapacityBuilder extends CSIStorageCapacityFluent implements VisitableBuilder {
   CSIStorageCapacityFluent fluent;

   public CSIStorageCapacityBuilder() {
      this(new CSIStorageCapacity());
   }

   public CSIStorageCapacityBuilder(CSIStorageCapacityFluent fluent) {
      this(fluent, new CSIStorageCapacity());
   }

   public CSIStorageCapacityBuilder(CSIStorageCapacityFluent fluent, CSIStorageCapacity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIStorageCapacityBuilder(CSIStorageCapacity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIStorageCapacity build() {
      CSIStorageCapacity buildable = new CSIStorageCapacity(this.fluent.getApiVersion(), this.fluent.getCapacity(), this.fluent.getKind(), this.fluent.getMaximumVolumeSize(), this.fluent.buildMetadata(), this.fluent.buildNodeTopology(), this.fluent.getStorageClassName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
