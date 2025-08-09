package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class OpaqueDeviceConfigurationBuilder extends OpaqueDeviceConfigurationFluent implements VisitableBuilder {
   OpaqueDeviceConfigurationFluent fluent;

   public OpaqueDeviceConfigurationBuilder() {
      this(new OpaqueDeviceConfiguration());
   }

   public OpaqueDeviceConfigurationBuilder(OpaqueDeviceConfigurationFluent fluent) {
      this(fluent, new OpaqueDeviceConfiguration());
   }

   public OpaqueDeviceConfigurationBuilder(OpaqueDeviceConfigurationFluent fluent, OpaqueDeviceConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public OpaqueDeviceConfigurationBuilder(OpaqueDeviceConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public OpaqueDeviceConfiguration build() {
      OpaqueDeviceConfiguration buildable = new OpaqueDeviceConfiguration(this.fluent.getDriver(), this.fluent.getParameters());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
