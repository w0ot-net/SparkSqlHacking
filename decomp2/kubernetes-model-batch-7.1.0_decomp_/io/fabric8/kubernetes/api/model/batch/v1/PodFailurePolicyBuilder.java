package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodFailurePolicyBuilder extends PodFailurePolicyFluent implements VisitableBuilder {
   PodFailurePolicyFluent fluent;

   public PodFailurePolicyBuilder() {
      this(new PodFailurePolicy());
   }

   public PodFailurePolicyBuilder(PodFailurePolicyFluent fluent) {
      this(fluent, new PodFailurePolicy());
   }

   public PodFailurePolicyBuilder(PodFailurePolicyFluent fluent, PodFailurePolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodFailurePolicyBuilder(PodFailurePolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodFailurePolicy build() {
      PodFailurePolicy buildable = new PodFailurePolicy(this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
