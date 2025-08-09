package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceRequestBuilder extends DeviceRequestFluent implements VisitableBuilder {
   DeviceRequestFluent fluent;

   public DeviceRequestBuilder() {
      this(new DeviceRequest());
   }

   public DeviceRequestBuilder(DeviceRequestFluent fluent) {
      this(fluent, new DeviceRequest());
   }

   public DeviceRequestBuilder(DeviceRequestFluent fluent, DeviceRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceRequestBuilder(DeviceRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceRequest build() {
      DeviceRequest buildable = new DeviceRequest(this.fluent.getAdminAccess(), this.fluent.getAllocationMode(), this.fluent.getCount(), this.fluent.getDeviceClassName(), this.fluent.getName(), this.fluent.buildSelectors());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
