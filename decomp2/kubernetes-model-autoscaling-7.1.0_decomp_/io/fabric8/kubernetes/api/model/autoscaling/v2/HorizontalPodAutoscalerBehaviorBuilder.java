package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerBehaviorBuilder extends HorizontalPodAutoscalerBehaviorFluent implements VisitableBuilder {
   HorizontalPodAutoscalerBehaviorFluent fluent;

   public HorizontalPodAutoscalerBehaviorBuilder() {
      this(new HorizontalPodAutoscalerBehavior());
   }

   public HorizontalPodAutoscalerBehaviorBuilder(HorizontalPodAutoscalerBehaviorFluent fluent) {
      this(fluent, new HorizontalPodAutoscalerBehavior());
   }

   public HorizontalPodAutoscalerBehaviorBuilder(HorizontalPodAutoscalerBehaviorFluent fluent, HorizontalPodAutoscalerBehavior instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerBehaviorBuilder(HorizontalPodAutoscalerBehavior instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscalerBehavior build() {
      HorizontalPodAutoscalerBehavior buildable = new HorizontalPodAutoscalerBehavior(this.fluent.buildScaleDown(), this.fluent.buildScaleUp());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
