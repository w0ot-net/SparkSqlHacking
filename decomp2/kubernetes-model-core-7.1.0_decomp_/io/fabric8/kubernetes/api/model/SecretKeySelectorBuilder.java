package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretKeySelectorBuilder extends SecretKeySelectorFluent implements VisitableBuilder {
   SecretKeySelectorFluent fluent;

   public SecretKeySelectorBuilder() {
      this(new SecretKeySelector());
   }

   public SecretKeySelectorBuilder(SecretKeySelectorFluent fluent) {
      this(fluent, new SecretKeySelector());
   }

   public SecretKeySelectorBuilder(SecretKeySelectorFluent fluent, SecretKeySelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretKeySelectorBuilder(SecretKeySelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretKeySelector build() {
      SecretKeySelector buildable = new SecretKeySelector(this.fluent.getKey(), this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
