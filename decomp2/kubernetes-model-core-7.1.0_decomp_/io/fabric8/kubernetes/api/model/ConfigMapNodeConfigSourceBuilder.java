package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapNodeConfigSourceBuilder extends ConfigMapNodeConfigSourceFluent implements VisitableBuilder {
   ConfigMapNodeConfigSourceFluent fluent;

   public ConfigMapNodeConfigSourceBuilder() {
      this(new ConfigMapNodeConfigSource());
   }

   public ConfigMapNodeConfigSourceBuilder(ConfigMapNodeConfigSourceFluent fluent) {
      this(fluent, new ConfigMapNodeConfigSource());
   }

   public ConfigMapNodeConfigSourceBuilder(ConfigMapNodeConfigSourceFluent fluent, ConfigMapNodeConfigSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapNodeConfigSourceBuilder(ConfigMapNodeConfigSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapNodeConfigSource build() {
      ConfigMapNodeConfigSource buildable = new ConfigMapNodeConfigSource(this.fluent.getKubeletConfigKey(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getResourceVersion(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
