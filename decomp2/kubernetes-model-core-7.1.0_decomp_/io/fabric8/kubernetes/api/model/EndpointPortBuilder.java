package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointPortBuilder extends EndpointPortFluent implements VisitableBuilder {
   EndpointPortFluent fluent;

   public EndpointPortBuilder() {
      this(new EndpointPort());
   }

   public EndpointPortBuilder(EndpointPortFluent fluent) {
      this(fluent, new EndpointPort());
   }

   public EndpointPortBuilder(EndpointPortFluent fluent, EndpointPort instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointPortBuilder(EndpointPort instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointPort build() {
      EndpointPort buildable = new EndpointPort(this.fluent.getAppProtocol(), this.fluent.getName(), this.fluent.getPort(), this.fluent.getProtocol());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
