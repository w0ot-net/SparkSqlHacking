package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerBuilder extends HorizontalPodAutoscalerFluent implements VisitableBuilder {
   HorizontalPodAutoscalerFluent fluent;

   public HorizontalPodAutoscalerBuilder() {
      this(new HorizontalPodAutoscaler());
   }

   public HorizontalPodAutoscalerBuilder(HorizontalPodAutoscalerFluent fluent) {
      this(fluent, new HorizontalPodAutoscaler());
   }

   public HorizontalPodAutoscalerBuilder(HorizontalPodAutoscalerFluent fluent, HorizontalPodAutoscaler instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerBuilder(HorizontalPodAutoscaler instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscaler build() {
      HorizontalPodAutoscaler buildable = new HorizontalPodAutoscaler(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
