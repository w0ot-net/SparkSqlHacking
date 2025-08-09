package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeMetricsBuilder extends NodeMetricsFluent implements VisitableBuilder {
   NodeMetricsFluent fluent;

   public NodeMetricsBuilder() {
      this(new NodeMetrics());
   }

   public NodeMetricsBuilder(NodeMetricsFluent fluent) {
      this(fluent, new NodeMetrics());
   }

   public NodeMetricsBuilder(NodeMetricsFluent fluent, NodeMetrics instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeMetricsBuilder(NodeMetrics instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeMetrics build() {
      NodeMetrics buildable = new NodeMetrics(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getTimestamp(), this.fluent.getUsage(), this.fluent.getWindow());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
