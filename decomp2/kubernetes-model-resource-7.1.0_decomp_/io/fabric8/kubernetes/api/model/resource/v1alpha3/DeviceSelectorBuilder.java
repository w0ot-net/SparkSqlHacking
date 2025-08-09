package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceSelectorBuilder extends DeviceSelectorFluent implements VisitableBuilder {
   DeviceSelectorFluent fluent;

   public DeviceSelectorBuilder() {
      this(new DeviceSelector());
   }

   public DeviceSelectorBuilder(DeviceSelectorFluent fluent) {
      this(fluent, new DeviceSelector());
   }

   public DeviceSelectorBuilder(DeviceSelectorFluent fluent, DeviceSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceSelectorBuilder(DeviceSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceSelector build() {
      DeviceSelector buildable = new DeviceSelector(this.fluent.buildCel());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
