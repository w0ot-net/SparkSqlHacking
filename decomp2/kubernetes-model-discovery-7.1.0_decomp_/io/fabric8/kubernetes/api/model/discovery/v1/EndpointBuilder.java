package io.fabric8.kubernetes.api.model.discovery.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointBuilder extends EndpointFluent implements VisitableBuilder {
   EndpointFluent fluent;

   public EndpointBuilder() {
      this(new Endpoint());
   }

   public EndpointBuilder(EndpointFluent fluent) {
      this(fluent, new Endpoint());
   }

   public EndpointBuilder(EndpointFluent fluent, Endpoint instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointBuilder(Endpoint instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Endpoint build() {
      Endpoint buildable = new Endpoint(this.fluent.getAddresses(), this.fluent.buildConditions(), this.fluent.getDeprecatedTopology(), this.fluent.buildHints(), this.fluent.getHostname(), this.fluent.getNodeName(), this.fluent.buildTargetRef(), this.fluent.getZone());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
