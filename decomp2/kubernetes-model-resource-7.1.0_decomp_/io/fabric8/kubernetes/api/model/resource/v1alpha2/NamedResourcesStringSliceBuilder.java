package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedResourcesStringSliceBuilder extends NamedResourcesStringSliceFluent implements VisitableBuilder {
   NamedResourcesStringSliceFluent fluent;

   public NamedResourcesStringSliceBuilder() {
      this(new NamedResourcesStringSlice());
   }

   public NamedResourcesStringSliceBuilder(NamedResourcesStringSliceFluent fluent) {
      this(fluent, new NamedResourcesStringSlice());
   }

   public NamedResourcesStringSliceBuilder(NamedResourcesStringSliceFluent fluent, NamedResourcesStringSlice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedResourcesStringSliceBuilder(NamedResourcesStringSlice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedResourcesStringSlice build() {
      NamedResourcesStringSlice buildable = new NamedResourcesStringSlice(this.fluent.getStrings());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
