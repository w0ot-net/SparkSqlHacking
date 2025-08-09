package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceAccountSubjectBuilder extends ServiceAccountSubjectFluent implements VisitableBuilder {
   ServiceAccountSubjectFluent fluent;

   public ServiceAccountSubjectBuilder() {
      this(new ServiceAccountSubject());
   }

   public ServiceAccountSubjectBuilder(ServiceAccountSubjectFluent fluent) {
      this(fluent, new ServiceAccountSubject());
   }

   public ServiceAccountSubjectBuilder(ServiceAccountSubjectFluent fluent, ServiceAccountSubject instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceAccountSubjectBuilder(ServiceAccountSubject instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceAccountSubject build() {
      ServiceAccountSubject buildable = new ServiceAccountSubject(this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
