package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeMetricsListBuilder extends NodeMetricsListFluent implements VisitableBuilder {
   NodeMetricsListFluent fluent;

   public NodeMetricsListBuilder() {
      this(new NodeMetricsList());
   }

   public NodeMetricsListBuilder(NodeMetricsListFluent fluent) {
      this(fluent, new NodeMetricsList());
   }

   public NodeMetricsListBuilder(NodeMetricsListFluent fluent, NodeMetricsList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeMetricsListBuilder(NodeMetricsList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeMetricsList build() {
      NodeMetricsList buildable = new NodeMetricsList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
