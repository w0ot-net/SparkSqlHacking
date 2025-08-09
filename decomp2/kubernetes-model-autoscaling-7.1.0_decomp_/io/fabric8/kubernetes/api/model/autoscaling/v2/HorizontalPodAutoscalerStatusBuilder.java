package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerStatusBuilder extends HorizontalPodAutoscalerStatusFluent implements VisitableBuilder {
   HorizontalPodAutoscalerStatusFluent fluent;

   public HorizontalPodAutoscalerStatusBuilder() {
      this(new HorizontalPodAutoscalerStatus());
   }

   public HorizontalPodAutoscalerStatusBuilder(HorizontalPodAutoscalerStatusFluent fluent) {
      this(fluent, new HorizontalPodAutoscalerStatus());
   }

   public HorizontalPodAutoscalerStatusBuilder(HorizontalPodAutoscalerStatusFluent fluent, HorizontalPodAutoscalerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerStatusBuilder(HorizontalPodAutoscalerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscalerStatus build() {
      HorizontalPodAutoscalerStatus buildable = new HorizontalPodAutoscalerStatus(this.fluent.buildConditions(), this.fluent.buildCurrentMetrics(), this.fluent.getCurrentReplicas(), this.fluent.getDesiredReplicas(), this.fluent.getLastScaleTime(), this.fluent.getObservedGeneration());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
