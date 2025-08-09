package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodMetricsListBuilder extends PodMetricsListFluent implements VisitableBuilder {
   PodMetricsListFluent fluent;

   public PodMetricsListBuilder() {
      this(new PodMetricsList());
   }

   public PodMetricsListBuilder(PodMetricsListFluent fluent) {
      this(fluent, new PodMetricsList());
   }

   public PodMetricsListBuilder(PodMetricsListFluent fluent, PodMetricsList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodMetricsListBuilder(PodMetricsList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodMetricsList build() {
      PodMetricsList buildable = new PodMetricsList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
