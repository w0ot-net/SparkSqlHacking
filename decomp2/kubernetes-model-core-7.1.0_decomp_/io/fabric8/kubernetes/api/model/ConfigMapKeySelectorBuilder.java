package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapKeySelectorBuilder extends ConfigMapKeySelectorFluent implements VisitableBuilder {
   ConfigMapKeySelectorFluent fluent;

   public ConfigMapKeySelectorBuilder() {
      this(new ConfigMapKeySelector());
   }

   public ConfigMapKeySelectorBuilder(ConfigMapKeySelectorFluent fluent) {
      this(fluent, new ConfigMapKeySelector());
   }

   public ConfigMapKeySelectorBuilder(ConfigMapKeySelectorFluent fluent, ConfigMapKeySelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapKeySelectorBuilder(ConfigMapKeySelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapKeySelector build() {
      ConfigMapKeySelector buildable = new ConfigMapKeySelector(this.fluent.getKey(), this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
