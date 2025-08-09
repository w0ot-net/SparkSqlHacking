package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClassListBuilder extends DeviceClassListFluent implements VisitableBuilder {
   DeviceClassListFluent fluent;

   public DeviceClassListBuilder() {
      this(new DeviceClassList());
   }

   public DeviceClassListBuilder(DeviceClassListFluent fluent) {
      this(fluent, new DeviceClassList());
   }

   public DeviceClassListBuilder(DeviceClassListFluent fluent, DeviceClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClassListBuilder(DeviceClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClassList build() {
      DeviceClassList buildable = new DeviceClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
