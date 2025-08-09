package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RollbackConfigBuilder extends RollbackConfigFluent implements VisitableBuilder {
   RollbackConfigFluent fluent;

   public RollbackConfigBuilder() {
      this(new RollbackConfig());
   }

   public RollbackConfigBuilder(RollbackConfigFluent fluent) {
      this(fluent, new RollbackConfig());
   }

   public RollbackConfigBuilder(RollbackConfigFluent fluent, RollbackConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RollbackConfigBuilder(RollbackConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RollbackConfig build() {
      RollbackConfig buildable = new RollbackConfig(this.fluent.getRevision());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
