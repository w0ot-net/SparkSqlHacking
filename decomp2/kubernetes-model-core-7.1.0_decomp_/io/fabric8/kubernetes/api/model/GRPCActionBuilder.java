package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCActionBuilder extends GRPCActionFluent implements VisitableBuilder {
   GRPCActionFluent fluent;

   public GRPCActionBuilder() {
      this(new GRPCAction());
   }

   public GRPCActionBuilder(GRPCActionFluent fluent) {
      this(fluent, new GRPCAction());
   }

   public GRPCActionBuilder(GRPCActionFluent fluent, GRPCAction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCActionBuilder(GRPCAction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCAction build() {
      GRPCAction buildable = new GRPCAction(this.fluent.getPort(), this.fluent.getService());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
