package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretEnvSourceBuilder extends SecretEnvSourceFluent implements VisitableBuilder {
   SecretEnvSourceFluent fluent;

   public SecretEnvSourceBuilder() {
      this(new SecretEnvSource());
   }

   public SecretEnvSourceBuilder(SecretEnvSourceFluent fluent) {
      this(fluent, new SecretEnvSource());
   }

   public SecretEnvSourceBuilder(SecretEnvSourceFluent fluent, SecretEnvSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretEnvSourceBuilder(SecretEnvSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretEnvSource build() {
      SecretEnvSource buildable = new SecretEnvSource(this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
