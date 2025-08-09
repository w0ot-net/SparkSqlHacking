package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceAccountListBuilder extends ServiceAccountListFluent implements VisitableBuilder {
   ServiceAccountListFluent fluent;

   public ServiceAccountListBuilder() {
      this(new ServiceAccountList());
   }

   public ServiceAccountListBuilder(ServiceAccountListFluent fluent) {
      this(fluent, new ServiceAccountList());
   }

   public ServiceAccountListBuilder(ServiceAccountListFluent fluent, ServiceAccountList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceAccountListBuilder(ServiceAccountList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceAccountList build() {
      ServiceAccountList buildable = new ServiceAccountList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
