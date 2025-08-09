package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CELDeviceSelectorBuilder extends CELDeviceSelectorFluent implements VisitableBuilder {
   CELDeviceSelectorFluent fluent;

   public CELDeviceSelectorBuilder() {
      this(new CELDeviceSelector());
   }

   public CELDeviceSelectorBuilder(CELDeviceSelectorFluent fluent) {
      this(fluent, new CELDeviceSelector());
   }

   public CELDeviceSelectorBuilder(CELDeviceSelectorFluent fluent, CELDeviceSelector instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CELDeviceSelectorBuilder(CELDeviceSelector instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CELDeviceSelector build() {
      CELDeviceSelector buildable = new CELDeviceSelector(this.fluent.getExpression());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
