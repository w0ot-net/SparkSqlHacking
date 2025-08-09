package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSINodeListBuilder extends CSINodeListFluent implements VisitableBuilder {
   CSINodeListFluent fluent;

   public CSINodeListBuilder() {
      this(new CSINodeList());
   }

   public CSINodeListBuilder(CSINodeListFluent fluent) {
      this(fluent, new CSINodeList());
   }

   public CSINodeListBuilder(CSINodeListFluent fluent, CSINodeList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSINodeListBuilder(CSINodeList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSINodeList build() {
      CSINodeList buildable = new CSINodeList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
