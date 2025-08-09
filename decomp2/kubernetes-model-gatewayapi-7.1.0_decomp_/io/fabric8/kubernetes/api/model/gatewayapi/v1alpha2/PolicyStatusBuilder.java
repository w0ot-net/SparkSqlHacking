package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PolicyStatusBuilder extends PolicyStatusFluent implements VisitableBuilder {
   PolicyStatusFluent fluent;

   public PolicyStatusBuilder() {
      this(new PolicyStatus());
   }

   public PolicyStatusBuilder(PolicyStatusFluent fluent) {
      this(fluent, new PolicyStatus());
   }

   public PolicyStatusBuilder(PolicyStatusFluent fluent, PolicyStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PolicyStatusBuilder(PolicyStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PolicyStatus build() {
      PolicyStatus buildable = new PolicyStatus(this.fluent.buildAncestors());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
