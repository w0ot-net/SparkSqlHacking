package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClassBuilder extends DeviceClassFluent implements VisitableBuilder {
   DeviceClassFluent fluent;

   public DeviceClassBuilder() {
      this(new DeviceClass());
   }

   public DeviceClassBuilder(DeviceClassFluent fluent) {
      this(fluent, new DeviceClass());
   }

   public DeviceClassBuilder(DeviceClassFluent fluent, DeviceClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClassBuilder(DeviceClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClass build() {
      DeviceClass buildable = new DeviceClass(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
