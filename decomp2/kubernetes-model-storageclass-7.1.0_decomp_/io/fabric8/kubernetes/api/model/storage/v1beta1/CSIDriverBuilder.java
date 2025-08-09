package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIDriverBuilder extends CSIDriverFluent implements VisitableBuilder {
   CSIDriverFluent fluent;

   public CSIDriverBuilder() {
      this(new CSIDriver());
   }

   public CSIDriverBuilder(CSIDriverFluent fluent) {
      this(fluent, new CSIDriver());
   }

   public CSIDriverBuilder(CSIDriverFluent fluent, CSIDriver instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIDriverBuilder(CSIDriver instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIDriver build() {
      CSIDriver buildable = new CSIDriver(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
