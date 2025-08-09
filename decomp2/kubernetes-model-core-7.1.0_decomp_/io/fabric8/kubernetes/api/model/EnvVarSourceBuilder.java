package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EnvVarSourceBuilder extends EnvVarSourceFluent implements VisitableBuilder {
   EnvVarSourceFluent fluent;

   public EnvVarSourceBuilder() {
      this(new EnvVarSource());
   }

   public EnvVarSourceBuilder(EnvVarSourceFluent fluent) {
      this(fluent, new EnvVarSource());
   }

   public EnvVarSourceBuilder(EnvVarSourceFluent fluent, EnvVarSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EnvVarSourceBuilder(EnvVarSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EnvVarSource build() {
      EnvVarSource buildable = new EnvVarSource(this.fluent.buildConfigMapKeyRef(), this.fluent.buildFieldRef(), this.fluent.buildResourceFieldRef(), this.fluent.buildSecretKeyRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
