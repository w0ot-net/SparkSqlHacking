package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectAltNameBuilder extends SubjectAltNameFluent implements VisitableBuilder {
   SubjectAltNameFluent fluent;

   public SubjectAltNameBuilder() {
      this(new SubjectAltName());
   }

   public SubjectAltNameBuilder(SubjectAltNameFluent fluent) {
      this(fluent, new SubjectAltName());
   }

   public SubjectAltNameBuilder(SubjectAltNameFluent fluent, SubjectAltName instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectAltNameBuilder(SubjectAltName instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SubjectAltName build() {
      SubjectAltName buildable = new SubjectAltName(this.fluent.getHostname(), this.fluent.getType(), this.fluent.getUri());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
