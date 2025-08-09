package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceAttributeBuilder extends DeviceAttributeFluent implements VisitableBuilder {
   DeviceAttributeFluent fluent;

   public DeviceAttributeBuilder() {
      this(new DeviceAttribute());
   }

   public DeviceAttributeBuilder(DeviceAttributeFluent fluent) {
      this(fluent, new DeviceAttribute());
   }

   public DeviceAttributeBuilder(DeviceAttributeFluent fluent, DeviceAttribute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceAttributeBuilder(DeviceAttribute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceAttribute build() {
      DeviceAttribute buildable = new DeviceAttribute(this.fluent.getBool(), this.fluent.getInt(), this.fluent.getString(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
