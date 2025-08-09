package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapVolumeSourceBuilder extends ConfigMapVolumeSourceFluent implements VisitableBuilder {
   ConfigMapVolumeSourceFluent fluent;

   public ConfigMapVolumeSourceBuilder() {
      this(new ConfigMapVolumeSource());
   }

   public ConfigMapVolumeSourceBuilder(ConfigMapVolumeSourceFluent fluent) {
      this(fluent, new ConfigMapVolumeSource());
   }

   public ConfigMapVolumeSourceBuilder(ConfigMapVolumeSourceFluent fluent, ConfigMapVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapVolumeSourceBuilder(ConfigMapVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapVolumeSource build() {
      ConfigMapVolumeSource buildable = new ConfigMapVolumeSource(this.fluent.getDefaultMode(), this.fluent.buildItems(), this.fluent.getName(), this.fluent.getOptional());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
