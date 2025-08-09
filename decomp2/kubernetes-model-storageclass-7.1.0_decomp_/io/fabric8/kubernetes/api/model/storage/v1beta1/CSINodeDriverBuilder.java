package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSINodeDriverBuilder extends CSINodeDriverFluent implements VisitableBuilder {
   CSINodeDriverFluent fluent;

   public CSINodeDriverBuilder() {
      this(new CSINodeDriver());
   }

   public CSINodeDriverBuilder(CSINodeDriverFluent fluent) {
      this(fluent, new CSINodeDriver());
   }

   public CSINodeDriverBuilder(CSINodeDriverFluent fluent, CSINodeDriver instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSINodeDriverBuilder(CSINodeDriver instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSINodeDriver build() {
      CSINodeDriver buildable = new CSINodeDriver(this.fluent.buildAllocatable(), this.fluent.getName(), this.fluent.getNodeID(), this.fluent.getTopologyKeys());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
