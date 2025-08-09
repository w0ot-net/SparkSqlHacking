package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DriverRequestsBuilder extends DriverRequestsFluent implements VisitableBuilder {
   DriverRequestsFluent fluent;

   public DriverRequestsBuilder() {
      this(new DriverRequests());
   }

   public DriverRequestsBuilder(DriverRequestsFluent fluent) {
      this(fluent, new DriverRequests());
   }

   public DriverRequestsBuilder(DriverRequestsFluent fluent, DriverRequests instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DriverRequestsBuilder(DriverRequests instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DriverRequests build() {
      DriverRequests buildable = new DriverRequests(this.fluent.getDriverName(), this.fluent.buildRequests(), this.fluent.getVendorParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
