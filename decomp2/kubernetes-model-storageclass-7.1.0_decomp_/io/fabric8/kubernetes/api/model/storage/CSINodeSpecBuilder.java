package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSINodeSpecBuilder extends CSINodeSpecFluent implements VisitableBuilder {
   CSINodeSpecFluent fluent;

   public CSINodeSpecBuilder() {
      this(new CSINodeSpec());
   }

   public CSINodeSpecBuilder(CSINodeSpecFluent fluent) {
      this(fluent, new CSINodeSpec());
   }

   public CSINodeSpecBuilder(CSINodeSpecFluent fluent, CSINodeSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSINodeSpecBuilder(CSINodeSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSINodeSpec build() {
      CSINodeSpec buildable = new CSINodeSpec(this.fluent.buildDrivers());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
