package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SuccessPolicyBuilder extends SuccessPolicyFluent implements VisitableBuilder {
   SuccessPolicyFluent fluent;

   public SuccessPolicyBuilder() {
      this(new SuccessPolicy());
   }

   public SuccessPolicyBuilder(SuccessPolicyFluent fluent) {
      this(fluent, new SuccessPolicy());
   }

   public SuccessPolicyBuilder(SuccessPolicyFluent fluent, SuccessPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SuccessPolicyBuilder(SuccessPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SuccessPolicy build() {
      SuccessPolicy buildable = new SuccessPolicy(this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
