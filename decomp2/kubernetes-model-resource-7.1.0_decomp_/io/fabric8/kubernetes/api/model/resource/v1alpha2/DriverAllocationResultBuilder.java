package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DriverAllocationResultBuilder extends DriverAllocationResultFluent implements VisitableBuilder {
   DriverAllocationResultFluent fluent;

   public DriverAllocationResultBuilder() {
      this(new DriverAllocationResult());
   }

   public DriverAllocationResultBuilder(DriverAllocationResultFluent fluent) {
      this(fluent, new DriverAllocationResult());
   }

   public DriverAllocationResultBuilder(DriverAllocationResultFluent fluent, DriverAllocationResult instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DriverAllocationResultBuilder(DriverAllocationResult instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DriverAllocationResult build() {
      DriverAllocationResult buildable = new DriverAllocationResult(this.fluent.buildNamedResources(), this.fluent.getVendorRequestParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
