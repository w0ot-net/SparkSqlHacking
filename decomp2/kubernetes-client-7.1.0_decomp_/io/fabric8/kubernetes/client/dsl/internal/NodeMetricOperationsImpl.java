package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetricsList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.NodeMetricOperation;
import java.util.Map;

public class NodeMetricOperationsImpl extends MetricOperationsImpl implements NodeMetricOperation {
   public NodeMetricOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client));
   }

   public NodeMetricOperationsImpl(OperationContext context) {
      super(context.withPlural("nodes"), NodeMetrics.class, NodeMetricsList.class);
   }

   public NodeMetrics metrics(String nodeName) {
      return (NodeMetrics)this.withName(nodeName).metric();
   }

   public NodeMetricOperation withLabels(Map labels) {
      return new NodeMetricOperationsImpl(this.context.withLabels(labels));
   }

   public NodeMetricOperation withName(String name) {
      return new NodeMetricOperationsImpl(this.context.withName(name));
   }

   public boolean isResourceNamespaced() {
      return false;
   }
}
