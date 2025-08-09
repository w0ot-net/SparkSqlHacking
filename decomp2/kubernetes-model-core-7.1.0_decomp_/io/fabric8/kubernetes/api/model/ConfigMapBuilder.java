package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapBuilder extends ConfigMapFluent implements VisitableBuilder {
   ConfigMapFluent fluent;

   public ConfigMapBuilder() {
      this(new ConfigMap());
   }

   public ConfigMapBuilder(ConfigMapFluent fluent) {
      this(fluent, new ConfigMap());
   }

   public ConfigMapBuilder(ConfigMapFluent fluent, ConfigMap instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapBuilder(ConfigMap instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMap build() {
      ConfigMap buildable = new ConfigMap(this.fluent.getApiVersion(), this.fluent.getBinaryData(), this.fluent.getData(), this.fluent.getImmutable(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
