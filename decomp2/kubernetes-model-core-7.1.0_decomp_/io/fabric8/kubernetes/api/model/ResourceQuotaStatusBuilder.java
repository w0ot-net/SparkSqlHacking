package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceQuotaStatusBuilder extends ResourceQuotaStatusFluent implements VisitableBuilder {
   ResourceQuotaStatusFluent fluent;

   public ResourceQuotaStatusBuilder() {
      this(new ResourceQuotaStatus());
   }

   public ResourceQuotaStatusBuilder(ResourceQuotaStatusFluent fluent) {
      this(fluent, new ResourceQuotaStatus());
   }

   public ResourceQuotaStatusBuilder(ResourceQuotaStatusFluent fluent, ResourceQuotaStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceQuotaStatusBuilder(ResourceQuotaStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceQuotaStatus build() {
      ResourceQuotaStatus buildable = new ResourceQuotaStatus(this.fluent.getHard(), this.fluent.getUsed());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
