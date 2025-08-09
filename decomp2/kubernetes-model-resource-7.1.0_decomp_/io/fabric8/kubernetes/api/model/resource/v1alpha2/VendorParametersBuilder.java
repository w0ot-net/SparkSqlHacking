package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VendorParametersBuilder extends VendorParametersFluent implements VisitableBuilder {
   VendorParametersFluent fluent;

   public VendorParametersBuilder() {
      this(new VendorParameters());
   }

   public VendorParametersBuilder(VendorParametersFluent fluent) {
      this(fluent, new VendorParameters());
   }

   public VendorParametersBuilder(VendorParametersFluent fluent, VendorParameters instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VendorParametersBuilder(VendorParameters instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VendorParameters build() {
      VendorParameters buildable = new VendorParameters(this.fluent.getDriverName(), this.fluent.getParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
