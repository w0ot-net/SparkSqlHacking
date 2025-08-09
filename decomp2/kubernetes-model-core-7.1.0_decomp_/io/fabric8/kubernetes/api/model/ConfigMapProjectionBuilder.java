package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapProjectionBuilder extends ConfigMapProjectionFluent implements VisitableBuilder {
   ConfigMapProjectionFluent fluent;

   public ConfigMapProjectionBuilder() {
      this(new ConfigMapProjection());
   }

   public ConfigMapProjectionBuilder(ConfigMapProjectionFluent fluent) {
      this(fluent, new ConfigMapProjection());
   }

   public ConfigMapProjectionBuilder(ConfigMapProjectionFluent fluent, ConfigMapProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapProjectionBuilder(ConfigMapProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapProjection build() {
      ConfigMapProjection buildable = new ConfigMapProjection(this.fluent.buildItems(), this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
