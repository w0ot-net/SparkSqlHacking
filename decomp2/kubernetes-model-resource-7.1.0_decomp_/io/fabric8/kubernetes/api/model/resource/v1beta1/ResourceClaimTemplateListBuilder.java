package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceClaimTemplateListBuilder extends ResourceClaimTemplateListFluent implements VisitableBuilder {
   ResourceClaimTemplateListFluent fluent;

   public ResourceClaimTemplateListBuilder() {
      this(new ResourceClaimTemplateList());
   }

   public ResourceClaimTemplateListBuilder(ResourceClaimTemplateListFluent fluent) {
      this(fluent, new ResourceClaimTemplateList());
   }

   public ResourceClaimTemplateListBuilder(ResourceClaimTemplateListFluent fluent, ResourceClaimTemplateList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceClaimTemplateListBuilder(ResourceClaimTemplateList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceClaimTemplateList build() {
      ResourceClaimTemplateList buildable = new ResourceClaimTemplateList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
