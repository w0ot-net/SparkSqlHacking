package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClassSpecBuilder extends DeviceClassSpecFluent implements VisitableBuilder {
   DeviceClassSpecFluent fluent;

   public DeviceClassSpecBuilder() {
      this(new DeviceClassSpec());
   }

   public DeviceClassSpecBuilder(DeviceClassSpecFluent fluent) {
      this(fluent, new DeviceClassSpec());
   }

   public DeviceClassSpecBuilder(DeviceClassSpecFluent fluent, DeviceClassSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClassSpecBuilder(DeviceClassSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClassSpec build() {
      DeviceClassSpec buildable = new DeviceClassSpec(this.fluent.buildConfig(), this.fluent.buildSelectors());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
