package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerMetricsBuilder extends ContainerMetricsFluent implements VisitableBuilder {
   ContainerMetricsFluent fluent;

   public ContainerMetricsBuilder() {
      this(new ContainerMetrics());
   }

   public ContainerMetricsBuilder(ContainerMetricsFluent fluent) {
      this(fluent, new ContainerMetrics());
   }

   public ContainerMetricsBuilder(ContainerMetricsFluent fluent, ContainerMetrics instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerMetricsBuilder(ContainerMetrics instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerMetrics build() {
      ContainerMetrics buildable = new ContainerMetrics(this.fluent.getName(), this.fluent.getUsage());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
