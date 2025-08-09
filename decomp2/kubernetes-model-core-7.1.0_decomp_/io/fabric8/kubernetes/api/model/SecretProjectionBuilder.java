package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretProjectionBuilder extends SecretProjectionFluent implements VisitableBuilder {
   SecretProjectionFluent fluent;

   public SecretProjectionBuilder() {
      this(new SecretProjection());
   }

   public SecretProjectionBuilder(SecretProjectionFluent fluent) {
      this(fluent, new SecretProjection());
   }

   public SecretProjectionBuilder(SecretProjectionFluent fluent, SecretProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretProjectionBuilder(SecretProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretProjection build() {
      SecretProjection buildable = new SecretProjection(this.fluent.buildItems(), this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
