package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReferenceGrantBuilder extends ReferenceGrantFluent implements VisitableBuilder {
   ReferenceGrantFluent fluent;

   public ReferenceGrantBuilder() {
      this(new ReferenceGrant());
   }

   public ReferenceGrantBuilder(ReferenceGrantFluent fluent) {
      this(fluent, new ReferenceGrant());
   }

   public ReferenceGrantBuilder(ReferenceGrantFluent fluent, ReferenceGrant instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReferenceGrantBuilder(ReferenceGrant instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReferenceGrant build() {
      ReferenceGrant buildable = new ReferenceGrant(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
