package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodStatusBuilder extends PodStatusFluent implements VisitableBuilder {
   PodStatusFluent fluent;

   public PodStatusBuilder() {
      this(new PodStatus());
   }

   public PodStatusBuilder(PodStatusFluent fluent) {
      this(fluent, new PodStatus());
   }

   public PodStatusBuilder(PodStatusFluent fluent, PodStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodStatusBuilder(PodStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodStatus build() {
      PodStatus buildable = new PodStatus(this.fluent.buildConditions(), this.fluent.buildContainerStatuses(), this.fluent.buildEphemeralContainerStatuses(), this.fluent.getHostIP(), this.fluent.buildHostIPs(), this.fluent.buildInitContainerStatuses(), this.fluent.getMessage(), this.fluent.getNominatedNodeName(), this.fluent.getPhase(), this.fluent.getPodIP(), this.fluent.buildPodIPs(), this.fluent.getQosClass(), this.fluent.getReason(), this.fluent.getResize(), this.fluent.buildResourceClaimStatuses(), this.fluent.getStartTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
