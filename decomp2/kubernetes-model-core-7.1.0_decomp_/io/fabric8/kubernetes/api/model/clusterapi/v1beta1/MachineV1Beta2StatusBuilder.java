package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineV1Beta2StatusBuilder extends MachineV1Beta2StatusFluent implements VisitableBuilder {
   MachineV1Beta2StatusFluent fluent;

   public MachineV1Beta2StatusBuilder() {
      this(new MachineV1Beta2Status());
   }

   public MachineV1Beta2StatusBuilder(MachineV1Beta2StatusFluent fluent) {
      this(fluent, new MachineV1Beta2Status());
   }

   public MachineV1Beta2StatusBuilder(MachineV1Beta2StatusFluent fluent, MachineV1Beta2Status instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineV1Beta2StatusBuilder(MachineV1Beta2Status instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineV1Beta2Status build() {
      MachineV1Beta2Status buildable = new MachineV1Beta2Status(this.fluent.buildConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
