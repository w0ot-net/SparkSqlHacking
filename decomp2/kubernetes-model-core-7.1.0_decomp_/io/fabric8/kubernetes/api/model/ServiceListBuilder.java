package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceListBuilder extends ServiceListFluent implements VisitableBuilder {
   ServiceListFluent fluent;

   public ServiceListBuilder() {
      this(new ServiceList());
   }

   public ServiceListBuilder(ServiceListFluent fluent) {
      this(fluent, new ServiceList());
   }

   public ServiceListBuilder(ServiceListFluent fluent, ServiceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceListBuilder(ServiceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceList build() {
      ServiceList buildable = new ServiceList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
