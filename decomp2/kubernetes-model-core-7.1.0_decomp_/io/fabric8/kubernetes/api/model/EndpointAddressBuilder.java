package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointAddressBuilder extends EndpointAddressFluent implements VisitableBuilder {
   EndpointAddressFluent fluent;

   public EndpointAddressBuilder() {
      this(new EndpointAddress());
   }

   public EndpointAddressBuilder(EndpointAddressFluent fluent) {
      this(fluent, new EndpointAddress());
   }

   public EndpointAddressBuilder(EndpointAddressFluent fluent, EndpointAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointAddressBuilder(EndpointAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointAddress build() {
      EndpointAddress buildable = new EndpointAddress(this.fluent.getHostname(), this.fluent.getIp(), this.fluent.getNodeName(), this.fluent.buildTargetRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
