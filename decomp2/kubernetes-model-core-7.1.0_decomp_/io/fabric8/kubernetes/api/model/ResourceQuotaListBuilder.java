package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceQuotaListBuilder extends ResourceQuotaListFluent implements VisitableBuilder {
   ResourceQuotaListFluent fluent;

   public ResourceQuotaListBuilder() {
      this(new ResourceQuotaList());
   }

   public ResourceQuotaListBuilder(ResourceQuotaListFluent fluent) {
      this(fluent, new ResourceQuotaList());
   }

   public ResourceQuotaListBuilder(ResourceQuotaListFluent fluent, ResourceQuotaList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceQuotaListBuilder(ResourceQuotaList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceQuotaList build() {
      ResourceQuotaList buildable = new ResourceQuotaList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
