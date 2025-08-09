package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCHeaderMatchBuilder extends GRPCHeaderMatchFluent implements VisitableBuilder {
   GRPCHeaderMatchFluent fluent;

   public GRPCHeaderMatchBuilder() {
      this(new GRPCHeaderMatch());
   }

   public GRPCHeaderMatchBuilder(GRPCHeaderMatchFluent fluent) {
      this(fluent, new GRPCHeaderMatch());
   }

   public GRPCHeaderMatchBuilder(GRPCHeaderMatchFluent fluent, GRPCHeaderMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCHeaderMatchBuilder(GRPCHeaderMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCHeaderMatch build() {
      GRPCHeaderMatch buildable = new GRPCHeaderMatch(this.fluent.getName(), this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
