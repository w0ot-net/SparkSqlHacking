package io.fabric8.kubernetes.api.model.node.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuntimeClassSpecBuilder extends RuntimeClassSpecFluent implements VisitableBuilder {
   RuntimeClassSpecFluent fluent;

   public RuntimeClassSpecBuilder() {
      this(new RuntimeClassSpec());
   }

   public RuntimeClassSpecBuilder(RuntimeClassSpecFluent fluent) {
      this(fluent, new RuntimeClassSpec());
   }

   public RuntimeClassSpecBuilder(RuntimeClassSpecFluent fluent, RuntimeClassSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuntimeClassSpecBuilder(RuntimeClassSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RuntimeClassSpec build() {
      RuntimeClassSpec buildable = new RuntimeClassSpec(this.fluent.buildOverhead(), this.fluent.getRuntimeHandler(), this.fluent.buildScheduling());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
