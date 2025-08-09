package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapEnvSourceBuilder extends ConfigMapEnvSourceFluent implements VisitableBuilder {
   ConfigMapEnvSourceFluent fluent;

   public ConfigMapEnvSourceBuilder() {
      this(new ConfigMapEnvSource());
   }

   public ConfigMapEnvSourceBuilder(ConfigMapEnvSourceFluent fluent) {
      this(fluent, new ConfigMapEnvSource());
   }

   public ConfigMapEnvSourceBuilder(ConfigMapEnvSourceFluent fluent, ConfigMapEnvSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapEnvSourceBuilder(ConfigMapEnvSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapEnvSource build() {
      ConfigMapEnvSource buildable = new ConfigMapEnvSource(this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
