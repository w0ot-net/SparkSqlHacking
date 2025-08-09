package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClassConfigurationBuilder extends DeviceClassConfigurationFluent implements VisitableBuilder {
   DeviceClassConfigurationFluent fluent;

   public DeviceClassConfigurationBuilder() {
      this(new DeviceClassConfiguration());
   }

   public DeviceClassConfigurationBuilder(DeviceClassConfigurationFluent fluent) {
      this(fluent, new DeviceClassConfiguration());
   }

   public DeviceClassConfigurationBuilder(DeviceClassConfigurationFluent fluent, DeviceClassConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClassConfigurationBuilder(DeviceClassConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClassConfiguration build() {
      DeviceClassConfiguration buildable = new DeviceClassConfiguration(this.fluent.buildOpaque());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
