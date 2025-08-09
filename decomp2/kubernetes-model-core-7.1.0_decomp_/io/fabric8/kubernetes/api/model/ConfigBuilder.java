package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigBuilder extends ConfigFluent implements VisitableBuilder {
   ConfigFluent fluent;

   public ConfigBuilder() {
      this(new Config());
   }

   public ConfigBuilder(ConfigFluent fluent) {
      this(fluent, new Config());
   }

   public ConfigBuilder(ConfigFluent fluent, Config instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigBuilder(Config instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Config build() {
      Config buildable = new Config(this.fluent.getApiVersion(), this.fluent.buildClusters(), this.fluent.buildContexts(), this.fluent.getCurrentContext(), this.fluent.buildExtensions(), this.fluent.getKind(), this.fluent.buildPreferences(), this.fluent.buildUsers());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
