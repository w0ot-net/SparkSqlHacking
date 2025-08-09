package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIDriverListBuilder extends CSIDriverListFluent implements VisitableBuilder {
   CSIDriverListFluent fluent;

   public CSIDriverListBuilder() {
      this(new CSIDriverList());
   }

   public CSIDriverListBuilder(CSIDriverListFluent fluent) {
      this(fluent, new CSIDriverList());
   }

   public CSIDriverListBuilder(CSIDriverListFluent fluent, CSIDriverList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIDriverListBuilder(CSIDriverList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIDriverList build() {
      CSIDriverList buildable = new CSIDriverList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
