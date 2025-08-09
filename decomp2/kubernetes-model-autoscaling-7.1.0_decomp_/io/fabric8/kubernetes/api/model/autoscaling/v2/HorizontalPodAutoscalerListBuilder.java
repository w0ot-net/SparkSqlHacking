package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HorizontalPodAutoscalerListBuilder extends HorizontalPodAutoscalerListFluent implements VisitableBuilder {
   HorizontalPodAutoscalerListFluent fluent;

   public HorizontalPodAutoscalerListBuilder() {
      this(new HorizontalPodAutoscalerList());
   }

   public HorizontalPodAutoscalerListBuilder(HorizontalPodAutoscalerListFluent fluent) {
      this(fluent, new HorizontalPodAutoscalerList());
   }

   public HorizontalPodAutoscalerListBuilder(HorizontalPodAutoscalerListFluent fluent, HorizontalPodAutoscalerList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HorizontalPodAutoscalerListBuilder(HorizontalPodAutoscalerList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HorizontalPodAutoscalerList build() {
      HorizontalPodAutoscalerList buildable = new HorizontalPodAutoscalerList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
