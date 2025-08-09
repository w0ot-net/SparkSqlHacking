package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReferenceGrantToBuilder extends ReferenceGrantToFluent implements VisitableBuilder {
   ReferenceGrantToFluent fluent;

   public ReferenceGrantToBuilder() {
      this(new ReferenceGrantTo());
   }

   public ReferenceGrantToBuilder(ReferenceGrantToFluent fluent) {
      this(fluent, new ReferenceGrantTo());
   }

   public ReferenceGrantToBuilder(ReferenceGrantToFluent fluent, ReferenceGrantTo instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReferenceGrantToBuilder(ReferenceGrantTo instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReferenceGrantTo build() {
      ReferenceGrantTo buildable = new ReferenceGrantTo(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
