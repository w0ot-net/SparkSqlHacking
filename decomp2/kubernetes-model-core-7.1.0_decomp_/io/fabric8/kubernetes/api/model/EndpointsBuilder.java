package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointsBuilder extends EndpointsFluent implements VisitableBuilder {
   EndpointsFluent fluent;

   public EndpointsBuilder() {
      this(new Endpoints());
   }

   public EndpointsBuilder(EndpointsFluent fluent) {
      this(fluent, new Endpoints());
   }

   public EndpointsBuilder(EndpointsFluent fluent, Endpoints instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointsBuilder(Endpoints instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Endpoints build() {
      Endpoints buildable = new Endpoints(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSubsets());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
