package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BasicDeviceBuilder extends BasicDeviceFluent implements VisitableBuilder {
   BasicDeviceFluent fluent;

   public BasicDeviceBuilder() {
      this(new BasicDevice());
   }

   public BasicDeviceBuilder(BasicDeviceFluent fluent) {
      this(fluent, new BasicDevice());
   }

   public BasicDeviceBuilder(BasicDeviceFluent fluent, BasicDevice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BasicDeviceBuilder(BasicDevice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BasicDevice build() {
      BasicDevice buildable = new BasicDevice(this.fluent.getAttributes(), this.fluent.getCapacity());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
