package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReferenceGrantSpecBuilder extends ReferenceGrantSpecFluent implements VisitableBuilder {
   ReferenceGrantSpecFluent fluent;

   public ReferenceGrantSpecBuilder() {
      this(new ReferenceGrantSpec());
   }

   public ReferenceGrantSpecBuilder(ReferenceGrantSpecFluent fluent) {
      this(fluent, new ReferenceGrantSpec());
   }

   public ReferenceGrantSpecBuilder(ReferenceGrantSpecFluent fluent, ReferenceGrantSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReferenceGrantSpecBuilder(ReferenceGrantSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReferenceGrantSpec build() {
      ReferenceGrantSpec buildable = new ReferenceGrantSpec(this.fluent.buildFrom(), this.fluent.buildTo());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
