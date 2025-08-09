package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceSliceListBuilder extends ResourceSliceListFluent implements VisitableBuilder {
   ResourceSliceListFluent fluent;

   public ResourceSliceListBuilder() {
      this(new ResourceSliceList());
   }

   public ResourceSliceListBuilder(ResourceSliceListFluent fluent) {
      this(fluent, new ResourceSliceList());
   }

   public ResourceSliceListBuilder(ResourceSliceListFluent fluent, ResourceSliceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceSliceListBuilder(ResourceSliceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceSliceList build() {
      ResourceSliceList buildable = new ResourceSliceList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
