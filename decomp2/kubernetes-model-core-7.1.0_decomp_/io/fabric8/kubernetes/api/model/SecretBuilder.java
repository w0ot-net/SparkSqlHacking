package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretBuilder extends SecretFluent implements VisitableBuilder {
   SecretFluent fluent;

   public SecretBuilder() {
      this(new Secret());
   }

   public SecretBuilder(SecretFluent fluent) {
      this(fluent, new Secret());
   }

   public SecretBuilder(SecretFluent fluent, Secret instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretBuilder(Secret instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Secret build() {
      Secret buildable = new Secret(this.fluent.getApiVersion(), this.fluent.getData(), this.fluent.getImmutable(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getStringData(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
