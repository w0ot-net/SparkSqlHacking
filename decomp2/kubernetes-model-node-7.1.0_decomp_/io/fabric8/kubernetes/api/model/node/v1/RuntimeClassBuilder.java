package io.fabric8.kubernetes.api.model.node.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuntimeClassBuilder extends RuntimeClassFluent implements VisitableBuilder {
   RuntimeClassFluent fluent;

   public RuntimeClassBuilder() {
      this(new RuntimeClass());
   }

   public RuntimeClassBuilder(RuntimeClassFluent fluent) {
      this(fluent, new RuntimeClass());
   }

   public RuntimeClassBuilder(RuntimeClassFluent fluent, RuntimeClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuntimeClassBuilder(RuntimeClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RuntimeClass build() {
      RuntimeClass buildable = new RuntimeClass(this.fluent.getApiVersion(), this.fluent.getHandler(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildOverhead(), this.fluent.buildScheduling());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
