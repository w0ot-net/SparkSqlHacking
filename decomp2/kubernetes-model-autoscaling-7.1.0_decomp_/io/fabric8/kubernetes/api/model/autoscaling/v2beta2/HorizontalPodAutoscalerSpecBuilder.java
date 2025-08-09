package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerSpecBuilder extends HorizontalPodAutoscalerSpecFluent implements VisitableBuilder {
   HorizontalPodAutoscalerSpecFluent fluent;

   public HorizontalPodAutoscalerSpecBuilder() {
      this(new HorizontalPodAutoscalerSpec());
   }

   public HorizontalPodAutoscalerSpecBuilder(HorizontalPodAutoscalerSpecFluent fluent) {
      this(fluent, new HorizontalPodAutoscalerSpec());
   }

   public HorizontalPodAutoscalerSpecBuilder(HorizontalPodAutoscalerSpecFluent fluent, HorizontalPodAutoscalerSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerSpecBuilder(HorizontalPodAutoscalerSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscalerSpec build() {
      HorizontalPodAutoscalerSpec buildable = new HorizontalPodAutoscalerSpec(this.fluent.buildBehavior(), this.fluent.getMaxReplicas(), this.fluent.buildMetrics(), this.fluent.getMinReplicas(), this.fluent.buildScaleTargetRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
