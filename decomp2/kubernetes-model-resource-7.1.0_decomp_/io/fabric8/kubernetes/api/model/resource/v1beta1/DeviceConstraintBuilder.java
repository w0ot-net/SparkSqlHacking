package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceConstraintBuilder extends DeviceConstraintFluent implements VisitableBuilder {
   DeviceConstraintFluent fluent;

   public DeviceConstraintBuilder() {
      this(new DeviceConstraint());
   }

   public DeviceConstraintBuilder(DeviceConstraintFluent fluent) {
      this(fluent, new DeviceConstraint());
   }

   public DeviceConstraintBuilder(DeviceConstraintFluent fluent, DeviceConstraint instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceConstraintBuilder(DeviceConstraint instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceConstraint build() {
      DeviceConstraint buildable = new DeviceConstraint(this.fluent.getMatchAttribute(), this.fluent.getRequests());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
