package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceHandleBuilder extends ResourceHandleFluent implements VisitableBuilder {
   ResourceHandleFluent fluent;

   public ResourceHandleBuilder() {
      this(new ResourceHandle());
   }

   public ResourceHandleBuilder(ResourceHandleFluent fluent) {
      this(fluent, new ResourceHandle());
   }

   public ResourceHandleBuilder(ResourceHandleFluent fluent, ResourceHandle instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceHandleBuilder(ResourceHandle instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceHandle build() {
      ResourceHandle buildable = new ResourceHandle(this.fluent.getData(), this.fluent.getDriverName(), this.fluent.buildStructuredData());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
