package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConfigMapListBuilder extends ConfigMapListFluent implements VisitableBuilder {
   ConfigMapListFluent fluent;

   public ConfigMapListBuilder() {
      this(new ConfigMapList());
   }

   public ConfigMapListBuilder(ConfigMapListFluent fluent) {
      this(fluent, new ConfigMapList());
   }

   public ConfigMapListBuilder(ConfigMapListFluent fluent, ConfigMapList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConfigMapListBuilder(ConfigMapList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConfigMapList build() {
      ConfigMapList buildable = new ConfigMapList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
