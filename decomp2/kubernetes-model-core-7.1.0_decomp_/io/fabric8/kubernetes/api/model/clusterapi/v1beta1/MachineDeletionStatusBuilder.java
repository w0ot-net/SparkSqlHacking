package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MachineDeletionStatusBuilder extends MachineDeletionStatusFluent implements VisitableBuilder {
   MachineDeletionStatusFluent fluent;

   public MachineDeletionStatusBuilder() {
      this(new MachineDeletionStatus());
   }

   public MachineDeletionStatusBuilder(MachineDeletionStatusFluent fluent) {
      this(fluent, new MachineDeletionStatus());
   }

   public MachineDeletionStatusBuilder(MachineDeletionStatusFluent fluent, MachineDeletionStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MachineDeletionStatusBuilder(MachineDeletionStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MachineDeletionStatus build() {
      MachineDeletionStatus buildable = new MachineDeletionStatus(this.fluent.getNodeDrainStartTime(), this.fluent.getWaitForNodeVolumeDetachStartTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
