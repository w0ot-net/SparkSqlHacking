package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineAddressBuilder extends MachineAddressFluent implements VisitableBuilder {
   MachineAddressFluent fluent;

   public MachineAddressBuilder() {
      this(new MachineAddress());
   }

   public MachineAddressBuilder(MachineAddressFluent fluent) {
      this(fluent, new MachineAddress());
   }

   public MachineAddressBuilder(MachineAddressFluent fluent, MachineAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineAddressBuilder(MachineAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineAddress build() {
      MachineAddress buildable = new MachineAddress(this.fluent.getAddress(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
