package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointSubsetBuilder extends EndpointSubsetFluent implements VisitableBuilder {
   EndpointSubsetFluent fluent;

   public EndpointSubsetBuilder() {
      this(new EndpointSubset());
   }

   public EndpointSubsetBuilder(EndpointSubsetFluent fluent) {
      this(fluent, new EndpointSubset());
   }

   public EndpointSubsetBuilder(EndpointSubsetFluent fluent, EndpointSubset instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointSubsetBuilder(EndpointSubset instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointSubset build() {
      EndpointSubset buildable = new EndpointSubset(this.fluent.buildAddresses(), this.fluent.buildNotReadyAddresses(), this.fluent.buildPorts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
