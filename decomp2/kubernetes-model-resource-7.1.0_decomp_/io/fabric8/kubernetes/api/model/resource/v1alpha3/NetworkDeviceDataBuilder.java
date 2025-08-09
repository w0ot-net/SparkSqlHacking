package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NetworkDeviceDataBuilder extends NetworkDeviceDataFluent implements VisitableBuilder {
   NetworkDeviceDataFluent fluent;

   public NetworkDeviceDataBuilder() {
      this(new NetworkDeviceData());
   }

   public NetworkDeviceDataBuilder(NetworkDeviceDataFluent fluent) {
      this(fluent, new NetworkDeviceData());
   }

   public NetworkDeviceDataBuilder(NetworkDeviceDataFluent fluent, NetworkDeviceData instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NetworkDeviceDataBuilder(NetworkDeviceData instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NetworkDeviceData build() {
      NetworkDeviceData buildable = new NetworkDeviceData(this.fluent.getHardwareAddress(), this.fluent.getInterfaceName(), this.fluent.getIps());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
