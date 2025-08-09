package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetricsList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.PodMetricOperation;
import java.util.Map;

public class PodMetricOperationsImpl extends MetricOperationsImpl implements PodMetricOperation {
   public PodMetricOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client).withNamespace((String)null));
   }

   public PodMetricOperationsImpl(OperationContext context) {
      super(context.withPlural("pods"), PodMetrics.class, PodMetricsList.class);
   }

   public PodMetrics metrics(String namespace, String podName) {
      return (PodMetrics)this.inNamespace(namespace).withName(podName).metric();
   }

   public PodMetricsList metrics(String namespace) {
      return (PodMetricsList)this.inNamespace(namespace).metrics();
   }

   public PodMetricOperationsImpl inNamespace(String namespace) {
      return new PodMetricOperationsImpl(this.context.withNamespace(namespace));
   }

   public PodMetricOperation withName(String name) {
      return new PodMetricOperationsImpl(this.context.withName(name));
   }

   public PodMetricOperation withLabels(Map labels) {
      return new PodMetricOperationsImpl(this.context.withLabels(labels));
   }
}
