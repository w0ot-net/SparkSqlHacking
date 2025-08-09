package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StructuredResourceHandleBuilder extends StructuredResourceHandleFluent implements VisitableBuilder {
   StructuredResourceHandleFluent fluent;

   public StructuredResourceHandleBuilder() {
      this(new StructuredResourceHandle());
   }

   public StructuredResourceHandleBuilder(StructuredResourceHandleFluent fluent) {
      this(fluent, new StructuredResourceHandle());
   }

   public StructuredResourceHandleBuilder(StructuredResourceHandleFluent fluent, StructuredResourceHandle instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StructuredResourceHandleBuilder(StructuredResourceHandle instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StructuredResourceHandle build() {
      StructuredResourceHandle buildable = new StructuredResourceHandle(this.fluent.getNodeName(), this.fluent.buildResults(), this.fluent.getVendorClaimParameters(), this.fluent.getVendorClassParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
