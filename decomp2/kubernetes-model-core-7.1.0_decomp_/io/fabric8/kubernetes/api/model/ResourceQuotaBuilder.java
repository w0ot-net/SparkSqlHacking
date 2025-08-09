package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceQuotaBuilder extends ResourceQuotaFluent implements VisitableBuilder {
   ResourceQuotaFluent fluent;

   public ResourceQuotaBuilder() {
      this(new ResourceQuota());
   }

   public ResourceQuotaBuilder(ResourceQuotaFluent fluent) {
      this(fluent, new ResourceQuota());
   }

   public ResourceQuotaBuilder(ResourceQuotaFluent fluent, ResourceQuota instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceQuotaBuilder(ResourceQuota instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceQuota build() {
      ResourceQuota buildable = new ResourceQuota(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
