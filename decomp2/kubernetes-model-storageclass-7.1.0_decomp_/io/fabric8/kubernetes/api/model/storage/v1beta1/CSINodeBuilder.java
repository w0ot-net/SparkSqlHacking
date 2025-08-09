package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSINodeBuilder extends CSINodeFluent implements VisitableBuilder {
   CSINodeFluent fluent;

   public CSINodeBuilder() {
      this(new CSINode());
   }

   public CSINodeBuilder(CSINodeFluent fluent) {
      this(fluent, new CSINode());
   }

   public CSINodeBuilder(CSINodeFluent fluent, CSINode instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSINodeBuilder(CSINode instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSINode build() {
      CSINode buildable = new CSINode(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
