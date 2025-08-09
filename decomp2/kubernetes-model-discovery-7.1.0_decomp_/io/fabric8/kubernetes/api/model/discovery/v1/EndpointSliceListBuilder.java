package io.fabric8.kubernetes.api.model.discovery.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointSliceListBuilder extends EndpointSliceListFluent implements VisitableBuilder {
   EndpointSliceListFluent fluent;

   public EndpointSliceListBuilder() {
      this(new EndpointSliceList());
   }

   public EndpointSliceListBuilder(EndpointSliceListFluent fluent) {
      this(fluent, new EndpointSliceList());
   }

   public EndpointSliceListBuilder(EndpointSliceListFluent fluent, EndpointSliceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointSliceListBuilder(EndpointSliceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointSliceList build() {
      EndpointSliceList buildable = new EndpointSliceList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
