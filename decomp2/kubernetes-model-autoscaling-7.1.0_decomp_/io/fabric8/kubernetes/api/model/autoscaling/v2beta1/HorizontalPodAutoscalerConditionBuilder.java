package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerConditionBuilder extends HorizontalPodAutoscalerConditionFluent implements VisitableBuilder {
   HorizontalPodAutoscalerConditionFluent fluent;

   public HorizontalPodAutoscalerConditionBuilder() {
      this(new HorizontalPodAutoscalerCondition());
   }

   public HorizontalPodAutoscalerConditionBuilder(HorizontalPodAutoscalerConditionFluent fluent) {
      this(fluent, new HorizontalPodAutoscalerCondition());
   }

   public HorizontalPodAutoscalerConditionBuilder(HorizontalPodAutoscalerConditionFluent fluent, HorizontalPodAutoscalerCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerConditionBuilder(HorizontalPodAutoscalerCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscalerCondition build() {
      HorizontalPodAutoscalerCondition buildable = new HorizontalPodAutoscalerCondition(this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
