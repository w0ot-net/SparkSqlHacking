package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceAccountTokenProjectionBuilder extends ServiceAccountTokenProjectionFluent implements VisitableBuilder {
   ServiceAccountTokenProjectionFluent fluent;

   public ServiceAccountTokenProjectionBuilder() {
      this(new ServiceAccountTokenProjection());
   }

   public ServiceAccountTokenProjectionBuilder(ServiceAccountTokenProjectionFluent fluent) {
      this(fluent, new ServiceAccountTokenProjection());
   }

   public ServiceAccountTokenProjectionBuilder(ServiceAccountTokenProjectionFluent fluent, ServiceAccountTokenProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceAccountTokenProjectionBuilder(ServiceAccountTokenProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceAccountTokenProjection build() {
      ServiceAccountTokenProjection buildable = new ServiceAccountTokenProjection(this.fluent.getAudience(), this.fluent.getExpirationSeconds(), this.fluent.getPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
