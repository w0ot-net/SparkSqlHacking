package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceQuotaSpecBuilder extends ResourceQuotaSpecFluent implements VisitableBuilder {
   ResourceQuotaSpecFluent fluent;

   public ResourceQuotaSpecBuilder() {
      this(new ResourceQuotaSpec());
   }

   public ResourceQuotaSpecBuilder(ResourceQuotaSpecFluent fluent) {
      this(fluent, new ResourceQuotaSpec());
   }

   public ResourceQuotaSpecBuilder(ResourceQuotaSpecFluent fluent, ResourceQuotaSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceQuotaSpecBuilder(ResourceQuotaSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceQuotaSpec build() {
      ResourceQuotaSpec buildable = new ResourceQuotaSpec(this.fluent.getHard(), this.fluent.buildScopeSelector(), this.fluent.getScopes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
