package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonEndpointBuilder extends DaemonEndpointFluent implements VisitableBuilder {
   DaemonEndpointFluent fluent;

   public DaemonEndpointBuilder() {
      this(new DaemonEndpoint());
   }

   public DaemonEndpointBuilder(DaemonEndpointFluent fluent) {
      this(fluent, new DaemonEndpoint());
   }

   public DaemonEndpointBuilder(DaemonEndpointFluent fluent, DaemonEndpoint instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonEndpointBuilder(DaemonEndpoint instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonEndpoint build() {
      DaemonEndpoint buildable = new DaemonEndpoint(this.fluent.getPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
