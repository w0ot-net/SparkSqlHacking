package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceSliceBuilder extends ResourceSliceFluent implements VisitableBuilder {
   ResourceSliceFluent fluent;

   public ResourceSliceBuilder() {
      this(new ResourceSlice());
   }

   public ResourceSliceBuilder(ResourceSliceFluent fluent) {
      this(fluent, new ResourceSlice());
   }

   public ResourceSliceBuilder(ResourceSliceFluent fluent, ResourceSlice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceSliceBuilder(ResourceSlice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceSlice build() {
      ResourceSlice buildable = new ResourceSlice(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
