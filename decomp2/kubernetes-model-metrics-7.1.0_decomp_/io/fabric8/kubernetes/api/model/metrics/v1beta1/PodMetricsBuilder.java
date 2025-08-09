package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodMetricsBuilder extends PodMetricsFluent implements VisitableBuilder {
   PodMetricsFluent fluent;

   public PodMetricsBuilder() {
      this(new PodMetrics());
   }

   public PodMetricsBuilder(PodMetricsFluent fluent) {
      this(fluent, new PodMetrics());
   }

   public PodMetricsBuilder(PodMetricsFluent fluent, PodMetrics instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodMetricsBuilder(PodMetrics instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodMetrics build() {
      PodMetrics buildable = new PodMetrics(this.fluent.getApiVersion(), this.fluent.buildContainers(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getTimestamp(), this.fluent.getWindow());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
