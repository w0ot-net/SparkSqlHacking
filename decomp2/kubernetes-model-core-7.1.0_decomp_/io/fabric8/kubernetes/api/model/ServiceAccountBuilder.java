package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceAccountBuilder extends ServiceAccountFluent implements VisitableBuilder {
   ServiceAccountFluent fluent;

   public ServiceAccountBuilder() {
      this(new ServiceAccount());
   }

   public ServiceAccountBuilder(ServiceAccountFluent fluent) {
      this(fluent, new ServiceAccount());
   }

   public ServiceAccountBuilder(ServiceAccountFluent fluent, ServiceAccount instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceAccountBuilder(ServiceAccount instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceAccount build() {
      ServiceAccount buildable = new ServiceAccount(this.fluent.getApiVersion(), this.fluent.getAutomountServiceAccountToken(), this.fluent.buildImagePullSecrets(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSecrets());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
