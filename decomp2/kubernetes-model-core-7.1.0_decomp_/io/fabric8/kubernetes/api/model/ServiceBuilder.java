package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceBuilder extends ServiceFluent implements VisitableBuilder {
   ServiceFluent fluent;

   public ServiceBuilder() {
      this(new Service());
   }

   public ServiceBuilder(ServiceFluent fluent) {
      this(fluent, new Service());
   }

   public ServiceBuilder(ServiceFluent fluent, Service instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceBuilder(Service instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Service build() {
      Service buildable = new Service(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
