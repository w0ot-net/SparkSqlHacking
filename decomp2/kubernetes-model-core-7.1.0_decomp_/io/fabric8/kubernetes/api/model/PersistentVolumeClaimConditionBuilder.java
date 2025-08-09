package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PersistentVolumeClaimConditionBuilder extends PersistentVolumeClaimConditionFluent implements VisitableBuilder {
   PersistentVolumeClaimConditionFluent fluent;

   public PersistentVolumeClaimConditionBuilder() {
      this(new PersistentVolumeClaimCondition());
   }

   public PersistentVolumeClaimConditionBuilder(PersistentVolumeClaimConditionFluent fluent) {
      this(fluent, new PersistentVolumeClaimCondition());
   }

   public PersistentVolumeClaimConditionBuilder(PersistentVolumeClaimConditionFluent fluent, PersistentVolumeClaimCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PersistentVolumeClaimConditionBuilder(PersistentVolumeClaimCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PersistentVolumeClaimCondition build() {
      PersistentVolumeClaimCondition buildable = new PersistentVolumeClaimCondition(this.fluent.getLastProbeTime(), this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
