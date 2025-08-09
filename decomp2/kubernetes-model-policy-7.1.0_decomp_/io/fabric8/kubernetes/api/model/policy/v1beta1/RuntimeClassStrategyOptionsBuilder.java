package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuntimeClassStrategyOptionsBuilder extends RuntimeClassStrategyOptionsFluent implements VisitableBuilder {
   RuntimeClassStrategyOptionsFluent fluent;

   public RuntimeClassStrategyOptionsBuilder() {
      this(new RuntimeClassStrategyOptions());
   }

   public RuntimeClassStrategyOptionsBuilder(RuntimeClassStrategyOptionsFluent fluent) {
      this(fluent, new RuntimeClassStrategyOptions());
   }

   public RuntimeClassStrategyOptionsBuilder(RuntimeClassStrategyOptionsFluent fluent, RuntimeClassStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuntimeClassStrategyOptionsBuilder(RuntimeClassStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RuntimeClassStrategyOptions build() {
      RuntimeClassStrategyOptions buildable = new RuntimeClassStrategyOptions(this.fluent.getAllowedRuntimeClassNames(), this.fluent.getDefaultRuntimeClassName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
