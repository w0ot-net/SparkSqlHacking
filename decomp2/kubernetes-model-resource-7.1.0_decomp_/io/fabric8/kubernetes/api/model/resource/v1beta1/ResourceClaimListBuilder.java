package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimListBuilder extends ResourceClaimListFluent implements VisitableBuilder {
   ResourceClaimListFluent fluent;

   public ResourceClaimListBuilder() {
      this(new ResourceClaimList());
   }

   public ResourceClaimListBuilder(ResourceClaimListFluent fluent) {
      this(fluent, new ResourceClaimList());
   }

   public ResourceClaimListBuilder(ResourceClaimListFluent fluent, ResourceClaimList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimListBuilder(ResourceClaimList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimList build() {
      ResourceClaimList buildable = new ResourceClaimList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
