package io.fabric8.kubernetes.api.model.discovery.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointSliceBuilder extends EndpointSliceFluent implements VisitableBuilder {
   EndpointSliceFluent fluent;

   public EndpointSliceBuilder() {
      this(new EndpointSlice());
   }

   public EndpointSliceBuilder(EndpointSliceFluent fluent) {
      this(fluent, new EndpointSlice());
   }

   public EndpointSliceBuilder(EndpointSliceFluent fluent, EndpointSlice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointSliceBuilder(EndpointSlice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointSlice build() {
      EndpointSlice buildable = new EndpointSlice(this.fluent.getAddressType(), this.fluent.getApiVersion(), this.fluent.buildEndpoints(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildPorts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
