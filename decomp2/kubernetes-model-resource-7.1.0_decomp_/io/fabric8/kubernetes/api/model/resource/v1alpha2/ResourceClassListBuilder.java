package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClassListBuilder extends ResourceClassListFluent implements VisitableBuilder {
   ResourceClassListFluent fluent;

   public ResourceClassListBuilder() {
      this(new ResourceClassList());
   }

   public ResourceClassListBuilder(ResourceClassListFluent fluent) {
      this(fluent, new ResourceClassList());
   }

   public ResourceClassListBuilder(ResourceClassListFluent fluent, ResourceClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClassListBuilder(ResourceClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClassList build() {
      ResourceClassList buildable = new ResourceClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
