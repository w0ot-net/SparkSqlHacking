package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointsListBuilder extends EndpointsListFluent implements VisitableBuilder {
   EndpointsListFluent fluent;

   public EndpointsListBuilder() {
      this(new EndpointsList());
   }

   public EndpointsListBuilder(EndpointsListFluent fluent) {
      this(fluent, new EndpointsList());
   }

   public EndpointsListBuilder(EndpointsListFluent fluent, EndpointsList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointsListBuilder(EndpointsList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointsList build() {
      EndpointsList buildable = new EndpointsList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
