package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesIntSliceBuilder extends NamedResourcesIntSliceFluent implements VisitableBuilder {
   NamedResourcesIntSliceFluent fluent;

   public NamedResourcesIntSliceBuilder() {
      this(new NamedResourcesIntSlice());
   }

   public NamedResourcesIntSliceBuilder(NamedResourcesIntSliceFluent fluent) {
      this(fluent, new NamedResourcesIntSlice());
   }

   public NamedResourcesIntSliceBuilder(NamedResourcesIntSliceFluent fluent, NamedResourcesIntSlice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesIntSliceBuilder(NamedResourcesIntSlice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesIntSlice build() {
      NamedResourcesIntSlice buildable = new NamedResourcesIntSlice(this.fluent.getInts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
