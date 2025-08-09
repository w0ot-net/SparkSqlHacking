package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PolicyAncestorStatusBuilder extends PolicyAncestorStatusFluent implements VisitableBuilder {
   PolicyAncestorStatusFluent fluent;

   public PolicyAncestorStatusBuilder() {
      this(new PolicyAncestorStatus());
   }

   public PolicyAncestorStatusBuilder(PolicyAncestorStatusFluent fluent) {
      this(fluent, new PolicyAncestorStatus());
   }

   public PolicyAncestorStatusBuilder(PolicyAncestorStatusFluent fluent, PolicyAncestorStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PolicyAncestorStatusBuilder(PolicyAncestorStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PolicyAncestorStatus build() {
      PolicyAncestorStatus buildable = new PolicyAncestorStatus(this.fluent.buildAncestorRef(), this.fluent.getConditions(), this.fluent.getControllerName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
