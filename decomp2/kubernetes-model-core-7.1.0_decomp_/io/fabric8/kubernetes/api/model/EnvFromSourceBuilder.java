package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EnvFromSourceBuilder extends EnvFromSourceFluent implements VisitableBuilder {
   EnvFromSourceFluent fluent;

   public EnvFromSourceBuilder() {
      this(new EnvFromSource());
   }

   public EnvFromSourceBuilder(EnvFromSourceFluent fluent) {
      this(fluent, new EnvFromSource());
   }

   public EnvFromSourceBuilder(EnvFromSourceFluent fluent, EnvFromSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EnvFromSourceBuilder(EnvFromSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EnvFromSource build() {
      EnvFromSource buildable = new EnvFromSource(this.fluent.buildConfigMapRef(), this.fluent.getPrefix(), this.fluent.buildSecretRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
