package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCMethodMatchBuilder extends GRPCMethodMatchFluent implements VisitableBuilder {
   GRPCMethodMatchFluent fluent;

   public GRPCMethodMatchBuilder() {
      this(new GRPCMethodMatch());
   }

   public GRPCMethodMatchBuilder(GRPCMethodMatchFluent fluent) {
      this(fluent, new GRPCMethodMatch());
   }

   public GRPCMethodMatchBuilder(GRPCMethodMatchFluent fluent, GRPCMethodMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCMethodMatchBuilder(GRPCMethodMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCMethodMatch build() {
      GRPCMethodMatch buildable = new GRPCMethodMatch(this.fluent.getMethod(), this.fluent.getService(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
